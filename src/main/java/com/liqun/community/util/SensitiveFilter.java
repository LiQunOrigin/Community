package com.liqun.community.util;

import lombok.Getter;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import javax.annotation.PostConstruct;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.util
 * @className: SensitiveFilter
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/22 9:57
 */
@Component
public class SensitiveFilter {
    private static final Logger logger = LoggerFactory.getLogger("SensitiveFilter.class");

    //根节点
    private TrieNode rootNode = new TrieNode();
    //默认替换符
    private static final String DEFAULT_REPLACEMENT = "***";

    //初始化敏感词
    @PostConstruct
    public void init() {
        try (
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        ) {
            String keyword;
            while ((keyword = reader.readLine()) != null) {
                this.addKeyword(keyword);
            }
        } catch (IOException e) {
            logger.error("加载敏感词文件失败：" + e.getMessage());
        }
    }

    //添加敏感词
    private void addKeyword(String keyword) {
        TrieNode tempNode = rootNode;
        for (int i =0;i<keyword.length();i++){
            char c = keyword.charAt(i);

            TrieNode subNode = tempNode.getSubNode(c);
            if(subNode == null){
                //初始化子节点
                subNode = new TrieNode();
                tempNode.addSubNode(c,subNode);
            }

            //指向子节点，进入下一轮循环
            tempNode = subNode;
            //设置结束标识
            if(i == keyword.length()-1){
                tempNode.setKeyWordEnd(true);
            }
        }
    }

    /**
     * 过滤敏感词
     * @param text 待过滤的文本
     * @return 过滤后的文本
     */
    public String filter(String text) {
        if(StringUtils.isBlank(text)){
            return null;
        }
        //指针1
        TrieNode tempNode = rootNode;
        //指针2
        int begin = 0;
        //指针3
        int position = 0;
        //过滤后的文本
        StringBuilder result = new StringBuilder();

        while(position < text.length()){
            char c = text.charAt(position);
            //跳过符号
            if(isSymbol(c)){
                //若指针1处于根节点，将此符号计入结果，让指针2向下走一步
                if(tempNode == rootNode){
                    result.append(c);
                    begin++;
                }
                //无论符号在开头或中间，指针3都向下走一步
                position++;
                continue;
            }
            //检查下级节点
            tempNode = tempNode.getSubNode(c);
            if(tempNode == null){
                //以begin开头的字符串不是敏感词
                result.append(text.charAt(begin));
                //进入下一个位置
                position = ++begin;
                //重新指向根节点
                tempNode = rootNode;
            }
            //检查是否是敏感词的结尾
            else if(tempNode.isKeyWordEnd()){
                //是结尾，替换为***
                result.append(DEFAULT_REPLACEMENT);
                //进入下一个位置
                begin = ++position;
                //重新指向根节点
                tempNode = rootNode;
            }
            //不是结尾，指针3向下走一步
            else{
                position++;
            }
        }
        return result.toString();
    }


    //判断是否为符号
    private boolean isSymbol(char c) {
        //东亚
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }

    //前缀树
    private class TrieNode {
        //关键词结束标识
        private boolean isKeyWordEnd = false;
        //子节点
        private Map<Character, TrieNode> subNodes = new HashMap<>();

        //判断是否是关键词的结尾
        public boolean isKeyWordEnd() {
            return isKeyWordEnd;
        }

        public void setKeyWordEnd(boolean keyWordEnd) {
            isKeyWordEnd = keyWordEnd;
        }

        //添加子节点
        public void addSubNode(Character key, TrieNode node) {
            subNodes.put(key, node);
        }

        public TrieNode getSubNode(Character key) {
            return subNodes.get(key);
        }
    }


}

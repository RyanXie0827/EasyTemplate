package com.github.ryanxie0827.easytemplate.core;

/**
 * @description: 模板基础数据结构实体类，用于在各个 Provider 和执行器之间传递数据
 * @author: ryanxie0827
 */
public class TemplateData {

    /**
     * 模板名称 (如 Class, Dockerfile)
     */
    private String name;

    /**
     * 文件后缀 (如 java, vue, html。无后缀文件请传空字符串 "")
     */
    private String extension;

    /**
     * 是否为 Include 类型 (比如 File Header 属于公共片段，不是完整文件)
     */
    private boolean isInclude;

    /**
     * 模板完整的代码内容
     */
    private String content;

    /**
     * 全参构造函数
     *
     * @param name      模板名称
     * @param extension 文件后缀名
     * @param isInclude 是否为包含片段
     * @param content   模板内容
     */
    public TemplateData(String name, String extension, boolean isInclude, String content) {
        this.name = name;
        this.extension = extension;
        this.isInclude = isInclude;
        this.content = content;
    }

    // --- Getters & Setters ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean isInclude() {
        return isInclude;
    }

    public void setInclude(boolean include) {
        isInclude = include;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
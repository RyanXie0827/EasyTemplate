package com.github.ryanxie0827.easytemplate.provider;

import com.github.ryanxie0827.easytemplate.core.TemplateData;
import java.util.List;

/**
 * @description: 模板提供者接口，所有具体的分类模板（Java、前端、自定义等）都需要实现此接口
 * @author: ryanxie0827
 */
public interface TemplateProvider {

    /**
     * 获取该提供者名下的所有模板集合
     *
     * @return 模板数据列表
     */
    List<TemplateData> provideTemplates();
}
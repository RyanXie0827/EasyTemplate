package com.github.ryanxie0827.easytemplate;

import com.github.ryanxie0827.easytemplate.core.TemplateData;
import com.github.ryanxie0827.easytemplate.provider.TemplateProvider;
import com.github.ryanxie0827.easytemplate.provider.impl.*;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: EasyTemplate 核心引擎 - 负责全量模板写入逻辑
 * @author: ryanxie0827
 */
public class SetupTemplatesAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        executeSync(e.getProject(), false);
    }

    /**
     * 核心同步逻辑：聚合所有 Provider 并写入 IDEA 配置
     * @param project 当前项目
     * @param isSilent 是否静默执行
     */
    public static void executeSync(Project project, boolean isSilent) {
        FileTemplateManager templateManager = FileTemplateManager.getDefaultInstance();

        try {
            // 1. 手动注册所有领域的 Provider，确保 100% 加载成功
            List<TemplateProvider> providers = new ArrayList<>();
            providers.add(new JavaTemplateProvider());
            providers.add(new FrontendTemplateProvider());
            providers.add(new JvmLangTemplateProvider());
            providers.add(new DevOpsAndConfigTemplateProvider());
            providers.add(new PythonTemplateProvider());
            providers.add(new SystemLangTemplateProvider());
            providers.add(new ScriptingTemplateProvider());
            providers.add(new DataAndDocTemplateProvider());
            // 用户自定义放在最后，实现优先级覆盖
            providers.add(new UserCustomTemplateProvider());

            int count = 0;
            for (TemplateProvider provider : providers) {
                List<TemplateData> templates = provider.provideTemplates();
                for (TemplateData data : templates) {
                    if (data.isInclude()) {
                        saveIncludeTemplate(templateManager, data);
                    } else {
                        saveFileTemplate(templateManager, data);
                    }
                    count++;
                }
            }

            if (!isSilent && project != null) {
                Messages.showInfoMessage(project, "EasyTemplate 已成功同步 " + count + " 个模板！", "同步成功");
            }
        } catch (Exception ex) {
            if (!isSilent) {
                Messages.showErrorDialog("同步失败: " + ex.getMessage(), "EasyTemplate 错误");
            }
        }
    }

    private static void saveIncludeTemplate(FileTemplateManager manager, TemplateData data) {
        // 修复：使用 getPattern 获取内置 Includes 片段
        FileTemplate template = manager.getPattern(data.getName());
        if (template != null) {
            template.setText(data.getContent());
        } else {
            // 如果不存在，尝试作为普通模板处理或新增
            FileTemplate fallback = manager.getTemplate(data.getName());
            if (fallback == null) {
                fallback = manager.addTemplate(data.getName(), data.getExtension());
            }
            fallback.setText(data.getContent());
        }
    }

    private static void saveFileTemplate(FileTemplateManager manager, TemplateData data) {
        // 优先获取内置模板（如 Class），没有则获取自定义模板
        FileTemplate template = manager.getInternalTemplate(data.getName());
        if (template == null) {
            template = manager.getTemplate(data.getName());
        }

        if (template != null) {
            template.setText(data.getContent());
        } else {
            // 全新模板则新增
            FileTemplate newTpl = manager.addTemplate(data.getName(), data.getExtension());
            newTpl.setText(data.getContent());
        }
    }
}
package com.github.ryanxie0827.easytemplate;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @description: 适配 2024.1+ 版本的 IDEA 项目启动监听器。当项目打开时，后台静默执行模板配置。
 * @author: ryanxie0827
 */
public class TemplateStartupActivity implements ProjectActivity {

    /**
     * 在项目完全加载并打开时自动回调此方法
     * ProjectActivity 是 2024.1 及更高版本官方推荐的现代启动接口，替代了原有的 StartupActivity
     *
     * @param project 当前打开的项目
     * @param continuation Kotlin 协程延续对象（底层 API 需要）
     * @return 返回 Unit.INSTANCE 表示执行完毕
     */
    @Nullable
    @Override
    public Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {
        // 调用核心引擎执行同步，传入 isSilent = true
        // 这样每次打开项目都会在后台悄悄把模板刷好，用户全程无感知，也不会被弹窗打扰
        SetupTemplatesAction.executeSync(project, true);

        return Unit.INSTANCE;
    }
}
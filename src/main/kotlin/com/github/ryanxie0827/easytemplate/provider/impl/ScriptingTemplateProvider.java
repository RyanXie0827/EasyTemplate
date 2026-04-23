package com.github.ryanxie0827.easytemplate.provider.impl;

import com.github.ryanxie0827.easytemplate.core.TemplateData;
import com.github.ryanxie0827.easytemplate.provider.TemplateProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Shell 脚本、批处理及自动化构建工具模板提供者
 * @author: ryanxie0827
 */
public class ScriptingTemplateProvider implements TemplateProvider {

    @Override
    public List<TemplateData> provideTemplates() {
        List<TemplateData> list = new ArrayList<>();

        // 统一的 Bash / Shell 头部
        String bashHeader = "#!/bin/bash\n" +
                "# ==========================================\n" +
                "# @description: ${DESCRIPTION}\n" +
                "# @author: ${AUTHOR}\n" +
                "# @create: ${YEAR}-${MONTH}-${DAY}\n" +
                "# ==========================================\n\n";

        // Bash Script
        list.add(new TemplateData("Bash Script", "sh", false,
                bashHeader +
                        "set -e # Exit immediately if a command exits with a non-zero status.\n\n" +
                        "main() {\n" +
                        "    echo \"Running script...\"\n" +
                        "}\n\n" +
                        "main \"$@\"\n"
        ));

        // PowerShell Script
        String psHeader = "<#\n" +
                ".SYNOPSIS\n" +
                "    ${DESCRIPTION}\n" +
                ".AUTHOR\n" +
                "    ${AUTHOR}\n" +
                ".NOTES\n" +
                "    Created on ${YEAR}-${MONTH}-${DAY}\n" +
                "#>\n\n";

        list.add(new TemplateData("PowerShell Script", "ps1", false,
                psHeader +
                        "param (\n" +
                        "    # Define parameters here\n" +
                        ")\n\n" +
                        "Write-Output \"Running script...\"\n"
        ));

        // Makefile
        list.add(new TemplateData("Makefile", "", false,
                "# Project: ${PROJECT_NAME}\n" +
                        "# Description: ${DESCRIPTION}\n" +
                        "# Author: ${AUTHOR}\n\n" +
                        ".PHONY: all clean test\n\n" +
                        "all: build\n\n" +
                        "build:\n" +
                        "\t@echo \"Building...\"\n\n" +
                        "test:\n" +
                        "\t@echo \"Testing...\"\n\n" +
                        "clean:\n" +
                        "\t@echo \"Cleaning...\"\n"
        ));

        return list;
    }
}
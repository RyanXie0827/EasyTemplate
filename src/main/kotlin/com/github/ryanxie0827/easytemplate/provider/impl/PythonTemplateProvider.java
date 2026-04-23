package com.github.ryanxie0827.easytemplate.provider.impl;

import com.github.ryanxie0827.easytemplate.core.TemplateData;
import com.github.ryanxie0827.easytemplate.provider.TemplateProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Python、数据科学及 AI 相关模板提供者
 * @author: ryanxie0827
 */
public class PythonTemplateProvider implements TemplateProvider {

    @Override
    public List<TemplateData> provideTemplates() {
        List<TemplateData> list = new ArrayList<>();

        // 公共的 Python 文件头
        String pythonHeader = "# -*- coding: utf-8 -*-\n" +
                "\"\"\"\n" +
                "@description: ${DESCRIPTION}\n" +
                "@author: ${AUTHOR}\n" +
                "@create: ${YEAR}-${MONTH}-${DAY}\n" +
                "\"\"\"\n\n";

        // 1. 标准 Python 脚本
        list.add(new TemplateData("Python Script", "py", false,
                pythonHeader +
                        "def main():\n" +
                        "    pass\n\n" +
                        "if __name__ == '__main__':\n" +
                        "    main()\n"
        ));

        // 2. Python 单元测试 (unittest)
        list.add(new TemplateData("Python Unit Test", "py", false,
                pythonHeader +
                        "import unittest\n\n" +
                        "class Test${NAME}(unittest.TestCase):\n" +
                        "    def setUp(self):\n" +
                        "        pass\n\n" +
                        "    def test_example(self):\n" +
                        "        self.assertTrue(True)\n\n" +
                        "if __name__ == '__main__':\n" +
                        "    unittest.main()\n"
        ));

        // 3. requirements.txt 依赖配置
        list.add(new TemplateData("requirements.txt", "txt", false,
                "# Project: ${PROJECT_NAME}\n" +
                        "# Description: ${DESCRIPTION}\n" +
                        "# Author: ${AUTHOR}\n\n"
        ));

        return list;
    }
}
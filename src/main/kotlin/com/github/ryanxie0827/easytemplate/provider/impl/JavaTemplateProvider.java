package com.github.ryanxie0827.easytemplate.provider.impl;

import com.github.ryanxie0827.easytemplate.core.TemplateData;
import com.github.ryanxie0827.easytemplate.provider.TemplateProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Java 体系默认模板提供者
 * @author: ryanxie0827
 */
public class JavaTemplateProvider implements TemplateProvider {

    @Override
    public List<TemplateData> provideTemplates() {
        List<TemplateData> list = new ArrayList<>();

        // ==========================================
        // 1. 公共的 Java 类头注释 (File Header)
        // [核心机制]：由于定义了 ${DESCRIPTION} 和 ${AUTHOR} 这两个非系统内置变量，
        // 任何引用了此 Header 的文件，在创建时都会触发 IDEA 的原生输入弹窗。
        // ==========================================
        list.add(new TemplateData("File Header", "java", true,
                "/**\n" +
                        " * @description: ${DESCRIPTION}\n" +
                        " * @author: ${AUTHOR}\n" +
                        " * @className: ${NAME}\n" +
                        " * @create: ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}\n" +
                        " **/"
        ));

        // ==========================================
        // 2. Java 核心系列
        // [复用说明]：下方所有的模板均通过 #parse("File Header.java") 动态引入了头部。
        // 因此，无论你是新建 Class、Interface 还是 Enum，都会触发上述的变量弹窗。
        // ==========================================

        list.add(new TemplateData("Class", "java", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME};#end\n" +
                        "\n" +
                        "#parse(\"File Header.java\")\n" +
                        "public class ${NAME} {\n" +
                        "    \n" +
                        "}\n"
        ));

        list.add(new TemplateData("Interface", "java", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME};#end\n" +
                        "\n" +
                        "#parse(\"File Header.java\")\n" +
                        "public interface ${NAME} {\n" +
                        "    \n" +
                        "}\n"
        ));

        list.add(new TemplateData("Enum", "java", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME};#end\n" +
                        "\n" +
                        "#parse(\"File Header.java\")\n" +
                        "public enum ${NAME} {\n" +
                        "    \n" +
                        "}\n"
        ));

        list.add(new TemplateData("Record", "java", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME};#end\n" +
                        "\n" +
                        "#parse(\"File Header.java\")\n" +
                        "public record ${NAME}() {\n" +
                        "    \n" +
                        "}\n"
        ));

        list.add(new TemplateData("AnnotationType", "java", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME};#end\n" +
                        "\n" +
                        "#parse(\"File Header.java\")\n" +
                        "public @interface ${NAME} {\n" +
                        "    \n" +
                        "}\n"
        ));

        list.add(new TemplateData("package-info", "java", false,
                "#parse(\"File Header.java\")\n" +
                        "package ${PACKAGE_NAME};\n"
        ));

        list.add(new TemplateData("module-info", "java", false,
                "#parse(\"File Header.java\")\n" +
                        "module ${MODULE_NAME} {\n" +
                        "    \n" +
                        "}\n"
        ));

        return list;
    }
}
package com.github.ryanxie0827.easytemplate.provider.impl;

import com.github.ryanxie0827.easytemplate.core.TemplateData;
import com.github.ryanxie0827.easytemplate.provider.TemplateProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Kotlin、Groovy 及 JavaFX 模板提供者
 * @author: ryanxie0827
 */
public class JvmLangTemplateProvider implements TemplateProvider {

    @Override
    public List<TemplateData> provideTemplates() {
        List<TemplateData> list = new ArrayList<>();

        // Kotlin 系列
        list.add(new TemplateData("Kotlin File", "kt", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME}\n#end\n\n#parse(\"File Header.java\")\n"
        ));
        list.add(new TemplateData("Kotlin Class", "kt", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME}\n#end\n\n#parse(\"File Header.java\")\nclass ${NAME} {\n}\n"
        ));
        list.add(new TemplateData("Kotlin Enum", "kt", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME}\n#end\n\n#parse(\"File Header.java\")\nenum class ${NAME} {\n}\n"
        ));
        list.add(new TemplateData("Kotlin Interface", "kt", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME}\n#end\n\n#parse(\"File Header.java\")\ninterface ${NAME} {\n}\n"
        ));
        list.add(new TemplateData("Kotlin Worksheet", "ws.kts", false, "// Kotlin Worksheet\n"));
        list.add(new TemplateData("Kotlin Script", "kts", false, "// Kotlin Script\n"));

        // Groovy 系列
        list.add(new TemplateData("Groovy Class", "groovy", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME}\n#end\n\n#parse(\"File Header.java\")\nclass ${NAME} {\n}\n"
        ));
        list.add(new TemplateData("Groovy Interface", "groovy", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME}\n#end\n\n#parse(\"File Header.java\")\ninterface ${NAME} {\n}\n"
        ));
        list.add(new TemplateData("Groovy Trait", "groovy", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME}\n#end\n\n#parse(\"File Header.java\")\ntrait ${NAME} {\n}\n"
        ));
        list.add(new TemplateData("Groovy Enum", "groovy", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME}\n#end\n\n#parse(\"File Header.java\")\nenum ${NAME} {\n}\n"
        ));
        list.add(new TemplateData("Groovy Annotation", "groovy", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME}\n#end\n\n#parse(\"File Header.java\")\n@interface ${NAME} {\n}\n"
        ));
        list.add(new TemplateData("Groovy Script", "groovy", false, "// Groovy Script\n"));
        list.add(new TemplateData("Groovy DSL Script", "gdsl", false, "// Groovy DSL Script\n"));
        list.add(new TemplateData("Gant Script", "gant", false, "// Gant Script\n"));

        // JavaFX
        list.add(new TemplateData("JavaFXApplication", "java", false,
                "#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != \"\")package ${PACKAGE_NAME};\n#end\n\nimport javafx.application.Application;\nimport javafx.stage.Stage;\n\n#parse(\"File Header.java\")\npublic class ${NAME} extends Application {\n\n    public static void main(String[] args) {\n        launch(args);\n    }\n\n    @Override\n    public void start(Stage primaryStage) {\n        \n    }\n}\n"
        ));

        return list;
    }
}
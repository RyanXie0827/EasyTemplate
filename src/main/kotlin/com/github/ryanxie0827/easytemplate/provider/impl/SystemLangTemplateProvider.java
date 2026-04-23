package com.github.ryanxie0827.easytemplate.provider.impl;

import com.github.ryanxie0827.easytemplate.core.TemplateData;
import com.github.ryanxie0827.easytemplate.provider.TemplateProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Go、Rust、C、C++ 等系统级语言模板提供者
 * @author: ryanxie0827
 */
public class SystemLangTemplateProvider implements TemplateProvider {

    @Override
    public List<TemplateData> provideTemplates() {
        List<TemplateData> list = new ArrayList<>();

        // Go 语言
        String cStyleHeader = "/*\n" +
                " * @description: ${DESCRIPTION}\n" +
                " * @author: ${AUTHOR}\n" +
                " * @create: ${YEAR}-${MONTH}-${DAY}\n" +
                " */\n";

        list.add(new TemplateData("Go File", "go", false,
                cStyleHeader +
                        "package ${GO_PACKAGE_NAME}\n\n" +
                        "import \"fmt\"\n\n" +
                        "func main() {\n" +
                        "    fmt.Println(\"Hello, World!\")\n" +
                        "}\n"
        ));

        // Rust 语言
        String rustHeader = "// @description: ${DESCRIPTION}\n" +
                "// @author: ${AUTHOR}\n" +
                "// @create: ${YEAR}-${MONTH}-${DAY}\n\n";

        list.add(new TemplateData("Rust File", "rs", false,
                rustHeader +
                        "fn main() {\n" +
                        "    println!(\"Hello, world!\");\n" +
                        "}\n"
        ));

        // C / C++ 家族
        list.add(new TemplateData("C Source File", "c", false,
                cStyleHeader +
                        "#include <stdio.h>\n\n" +
                        "int main(void) {\n" +
                        "    printf(\"Hello, World!\\n\");\n" +
                        "    return 0;\n" +
                        "}\n"
        ));

        list.add(new TemplateData("C++ Source File", "cpp", false,
                cStyleHeader +
                        "#include <iostream>\n\n" +
                        "int main() {\n" +
                        "    std::cout << \"Hello, World!\" << std::endl;\n" +
                        "    return 0;\n" +
                        "}\n"
        ));

        list.add(new TemplateData("C Header File", "h", false,
                cStyleHeader +
                        "#ifndef ${NAME}_H\n" +
                        "#define ${NAME}_H\n\n" +
                        "// Declarations here\n\n" +
                        "#endif // ${NAME}_H\n"
        ));

        return list;
    }
}
package com.github.ryanxie0827.easytemplate.provider.impl;

import com.github.ryanxie0827.easytemplate.core.TemplateData;
import com.github.ryanxie0827.easytemplate.provider.TemplateProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 前端与 Web 技术栈模板提供者
 * @author: ryanxie0827
 */
public class FrontendTemplateProvider implements TemplateProvider {

    @Override
    public List<TemplateData> provideTemplates() {
        List<TemplateData> list = new ArrayList<>();

        // HTML & CSS 系列
        list.add(new TemplateData("HTML File", "html", false,
                "<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>${NAME}</title>\n</head>\n<body>\n    \n</body>\n</html>"
        ));

        String cssHeader = "/**\n * @description: ${DESCRIPTION}\n * @author: ${AUTHOR}\n * @create: ${YEAR}-${MONTH}-${DAY}\n */\n";
        list.add(new TemplateData("CSS File", "css", false, cssHeader + ".${NAME} {\n\n}"));
        list.add(new TemplateData("Less File", "less", false, cssHeader + ".${NAME} {\n\n}"));
        list.add(new TemplateData("Sass File", "sass", false, cssHeader + ".${NAME}\n  // body"));
        list.add(new TemplateData("SCSS File", "scss", false, cssHeader + ".${NAME} {\n\n}"));
        list.add(new TemplateData("PostCSS File", "pcss", false, cssHeader + ".${NAME} {\n\n}"));

        // JS / TS / React 系列
        String jsHeader = "/**\n * @description: ${DESCRIPTION}\n * @author: ${AUTHOR}\n * @create: ${YEAR}-${MONTH}-${DAY}\n */\n";
        list.add(new TemplateData("JavaScript File", "js", false, jsHeader));
        list.add(new TemplateData("TypeScript File", "ts", false, jsHeader));

        String reactComponent = jsHeader + "import React from 'react';\n\nexport const ${NAME} = () => {\n    return (\n        <div>\n            ${NAME} Component\n        </div>\n    );\n};\n";
        list.add(new TemplateData("JSX File", "jsx", false, reactComponent));
        list.add(new TemplateData("TypeScript JSX File", "tsx", false, reactComponent));

        // Vue 系列
        list.add(new TemplateData("Vue Composition API Component", "vue", false,
                "<template>\n  <div class=\"${NAME}\">\n    \n  </div>\n</template>\n\n<script setup lang=\"ts\">\n" + jsHeader + "</script>\n\n<style scoped lang=\"scss\">\n.${NAME} {\n\n}\n</style>"
        ));

        list.add(new TemplateData("Vue Options API Component", "vue", false,
                "<template>\n  <div class=\"${NAME}\">\n    \n  </div>\n</template>\n\n<script>\n" + jsHeader + "export default {\n  name: '${NAME}',\n  data() {\n    return {\n      \n    };\n  }\n};\n</script>\n\n<style scoped lang=\"scss\">\n.${NAME} {\n\n}\n</style>"
        ));

        list.add(new TemplateData("Vue Class API Component", "vue", false,
                "<template>\n  <div class=\"${NAME}\">\n    \n  </div>\n</template>\n\n<script lang=\"ts\">\nimport { Options, Vue } from 'vue-class-component';\n\n" + jsHeader + "@Options({\n  components: {\n  }\n})\nexport default class ${NAME} extends Vue {}\n</script>\n\n<style scoped lang=\"scss\">\n.${NAME} {\n\n}\n</style>"
        ));

        return list;
    }
}
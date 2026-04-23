package com.github.ryanxie0827.easytemplate.provider.impl;

import com.github.ryanxie0827.easytemplate.core.TemplateData;
import com.github.ryanxie0827.easytemplate.provider.TemplateProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 配置文件、DevOps、API测试脚本模板提供者
 * @author: ryanxie0827
 */
public class DevOpsAndConfigTemplateProvider implements TemplateProvider {

    @Override
    public List<TemplateData> provideTemplates() {
        List<TemplateData> list = new ArrayList<>();

        // 配置与脚本
        list.add(new TemplateData("XML Properties File", "xml", false,
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">\n<properties>\n    <comment>${DESCRIPTION}</comment>\n</properties>\n"
        ));
        list.add(new TemplateData("OpenRewrite Recipe", "yml", false,
                "---\ntype: specs.openrewrite.org/v1beta/recipe\nname: ${PACKAGE_NAME}.${NAME}\ndisplayName: ${NAME}\ndescription: ${DESCRIPTION}\nrecipeList:\n"
        ));
        list.add(new TemplateData("Gradle Build Script", "gradle", false,
                "// Gradle Build Script\n// Author: ${AUTHOR}\n\nplugins {\n    id 'java'\n}\n\ngroup = '${PACKAGE_NAME}'\nversion = '1.0-SNAPSHOT'\n\nrepositories {\n    mavenCentral()\n}\n\ndependencies {\n    testImplementation platform('org.junit:junit-bom:5.9.1')\n    testImplementation 'org.junit.jupiter:junit-jupiter'\n}\n"
        ));
        list.add(new TemplateData("Liquibase Changelog", "xml", false,
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<databaseChangeLog\n        xmlns=\"http://www.liquibase.org/xml/ns/dbchangelog\"\n        xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n        xsi:schemaLocation=\"http://www.liquibase.org/xml/ns/dbchangelog\n         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.0.xsd\">\n\n    \n    <changeSet id=\"1\" author=\"${AUTHOR}\">\n        \n    </changeSet>\n\n</databaseChangeLog>\n"
        ));

        // HTTP Scripts
        list.add(new TemplateData("API HTTP Request", "http", false, "### GET Request\nGET http://localhost:8080/api\nAccept: application/json\n"));
        list.add(new TemplateData("API HTTP Request Scratch", "http", false, "GET http://localhost:8080/api\n"));
        list.add(new TemplateData("HTTP Public Environment File", "env.json", false, "{\n  \"dev\": {\n    \"host\": \"localhost\"\n  }\n}\n"));
        list.add(new TemplateData("HTTP Private Environment File", "env.json", false, "{\n  \"dev\": {\n    \"password\": \"secret\"\n  }\n}\n"));

        // Node.js Configs
        list.add(new TemplateData("tsconfig.json", "json", false, "{\n  \"compilerOptions\": {\n    \"target\": \"es2016\",\n    \"module\": \"commonjs\",\n    \"strict\": true,\n    \"esModuleInterop\": true,\n    \"skipLibCheck\": true,\n    \"forceConsistentCasingInFileNames\": true\n  }\n}\n"));
        list.add(new TemplateData("package.json", "json", false, "{\n  \"name\": \"${NAME}\",\n  \"version\": \"1.0.0\",\n  \"description\": \"${DESCRIPTION}\",\n  \"main\": \"index.js\",\n  \"scripts\": {\n    \"test\": \"echo \\\"Error: no test specified\\\" && exit 1\"\n  },\n  \"author\": \"${AUTHOR}\",\n  \"license\": \"ISC\"\n}\n"));

        // DevOps: Docker & K8s
        list.add(new TemplateData("Dockerfile", "", false,
                "FROM eclipse-temurin:17-jre\nLABEL maintainer=\"${AUTHOR}\"\n\nWORKDIR /app\nCOPY target/*.jar app.jar\n\nEXPOSE 8080\nENTRYPOINT [\"java\", \"-jar\", \"app.jar\"]\n"
        ));

        String k8sHeader = "# Kubernetes Manifest\n# Resource: ${NAME}\n# Description: ${DESCRIPTION}\n# Author: ${AUTHOR}\n---\n";
        list.add(new TemplateData("Kubernetes Pod", "yaml", false, k8sHeader + "apiVersion: v1\nkind: Pod\nmetadata:\n  name: ${NAME}\nspec:\n  containers:\n  - name: ${NAME}\n    image: nginx:latest\n"));
        list.add(new TemplateData("Kubernetes Deployment", "yaml", false, k8sHeader + "apiVersion: apps/v1\nkind: Deployment\nmetadata:\n  name: ${NAME}\nspec:\n  replicas: 1\n  selector:\n    matchLabels:\n      app: ${NAME}\n  template:\n    metadata:\n      labels:\n        app: ${NAME}\n    spec:\n      containers:\n      - name: ${NAME}\n        image: nginx:latest\n"));
        list.add(new TemplateData("Kubernetes ConfigMap", "yaml", false, k8sHeader + "apiVersion: v1\nkind: ConfigMap\nmetadata:\n  name: ${NAME}\ndata:\n  key: value\n"));
        list.add(new TemplateData("Kubernetes Service", "yaml", false, k8sHeader + "apiVersion: v1\nkind: Service\nmetadata:\n  name: ${NAME}\nspec:\n  selector:\n    app: ${NAME}\n  ports:\n    - protocol: TCP\n      port: 80\n      targetPort: 8080\n"));
        list.add(new TemplateData("Kubernetes Ingress", "yaml", false, k8sHeader + "apiVersion: networking.k8s.io/v1\nkind: Ingress\nmetadata:\n  name: ${NAME}\nspec:\n  rules:\n  - host: chart-example.local\n    http:\n      paths:\n      - path: /\n        pathType: ImplementationSpecific\n        backend:\n          service:\n            name: ${NAME}\n            port:\n              number: 80\n"));
        list.add(new TemplateData("Kubernetes CronJob", "yaml", false, k8sHeader + "apiVersion: batch/v1\nkind: CronJob\nmetadata:\n  name: ${NAME}\nspec:\n  schedule: \"* * * * *\"\n  jobTemplate:\n    spec:\n      template:\n        spec:\n          containers:\n          - name: ${NAME}\n            image: busybox:1.28\n            imagePullPolicy: IfNotPresent\n            command:\n            - /bin/sh\n            - -c\n            - date; echo Hello from the Kubernetes cluster\n          restartPolicy: OnFailure\n"));

        return list;
    }
}
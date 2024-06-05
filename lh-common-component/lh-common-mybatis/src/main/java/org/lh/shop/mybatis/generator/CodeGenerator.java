package org.lh.shop.mybatis.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * @author wuYf
 * @date 2024/6/4 11:43
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://121.37.24.51:3346/lh-shop?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "pwd123";
        String tableName = "sys_dept";
        String tablePrefix = "t_";
        String modelName = "sys";
        String entityPackageName = "domain";
//        String projectPath = System.getProperty("user.dir");
        String projectPath = "C:/code/lh-shop-cloud/lh-shop-model/lh-shop-system";
        String outputPath = projectPath + "/src/main/java";
        String xmlPath = projectPath + "/src/main/resources/mapper/" ;
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> builder.author("wuYf").disableOpenDir().outputDir(outputPath))

                .packageConfig(builder -> builder.parent("org.lh.shop").moduleName(modelName).entity(entityPackageName).pathInfo(Collections.singletonMap(OutputFile.mapperXml, xmlPath)))

                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.controllerBuilder().enableRestStyle();
                    builder.addInclude(new String[] { tableName }).addTablePrefix(new String[] { tablePrefix });
                })
                .execute();
        System.out.println(tableName + "执行完成");
    }
}

package com.hbnu.gradesign.config;

import com.hbnu.gradesign.entity.GeneratorEntity;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * 逆向工程生成自定义注释
 */
public class RemarkCommentGenerator implements CommentGenerator {
	private Properties properties;

	public RemarkCommentGenerator() {
		properties = new Properties();
	}

	@Override
	public void addConfigurationProperties(Properties properties) {
		// 获取自定义的 properties
		this.properties.putAll(properties);
	}

	/**
	 * 自定义实体类字段注释
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		String author = properties.getProperty("author");
		String dateFormat = properties.getProperty("dateFormat", "yyyy-MM-dd");
		SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

		FullyQualifiedTable fullyQualifiedTable = introspectedTable.getFullyQualifiedTable();

//		Field field = new Field();
//		field.setVisibility(JavaVisibility.PRIVATE);
//		field.setType(new FullyQualifiedJavaType("long"));
//		field.setStatic(true);
//		field.setFinal(true);
//		field.setName("serialVersionUID");
//		field.setInitializationString("1L");
//		topLevelClass.addField(field);

//		FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("Serializable");
//		FullyQualifiedJavaType imp = new FullyQualifiedJavaType("java.io.Serializable");
//		topLevelClass.addSuperInterface(fqjt);
//		topLevelClass.addImportedType(imp);

		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
		topLevelClass.addJavaDocLine(" * " + fullyQualifiedTable.getIntrospectedTableName());
		topLevelClass.addJavaDocLine(" * @author " + author);
		topLevelClass.addJavaDocLine(" * @date " + dateFormatter.format(new Date()));
		topLevelClass.addJavaDocLine(" */");
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {

	}

	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

	}

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {

	}

	@Override
	public void addComment(XmlElement xmlElement) {

	}

	@Override
	public void addRootComment(XmlElement rootElement) {

	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

	}

	@Override
	public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

	}

	/**
	 * 自定义实体类注释
	 * @param field
	 * @param introspectedTable
	 * @param introspectedColumn
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

		Class<GeneratorEntity> baseEntityClass = GeneratorEntity.class;

		java.lang.reflect.Field[] declaredFields = baseEntityClass.getDeclaredFields();

		boolean isSet = true;

		for (java.lang.reflect.Field ignored : declaredFields) {

			ignored.setAccessible(true);

			if (ignored.getName().equals(introspectedColumn.getJavaProperty())) {
				isSet = false;
				continue;
			}

		}

		// 获取列注释
		String remarks = introspectedColumn.getRemarks();

		if (isSet == true) {
			field.addJavaDocLine("/**");
			if (!remarks.equals("")) {
				field.addJavaDocLine(" * " + remarks);
			}
			field.addJavaDocLine(" * " + introspectedColumn.getActualColumnName());
			field.addJavaDocLine(" */");
		}

	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

	}

}

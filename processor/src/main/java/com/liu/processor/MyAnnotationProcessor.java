package com.liu.processor;

import com.google.auto.service.AutoService;
import com.liu.annotation.MyAnnotation;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.naming.Context;

@AutoService(Processor.class)
public class MyAnnotationProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(MyAnnotation.class.getCanonicalName());
        return supportTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 创建Java类【你的类名】
//        TypeSpec autoClass = TypeSpec.classBuilder("AutoClass").build();
//
//        // 创建Java文档【这里定义了你的包名，随便写即可】
//        JavaFile javaFile = JavaFile.builder("com.apt.demo", autoClass).build();
//
//        // 将文档写入
//        try {
////            javaFile.writeTo(processingEnv.getFiler());
//            javaFile.writeTo(generateActivityFile("AutoClass","com.apt.demo",set));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < set.size(); i++) {
//            try {
//                TypeElement mTypeElement = set[i];
//                generateActivityFile((((TypeElement) (set[i]))), "com.apt.demo").writeTo(processingEnv.getFiler());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(MyAnnotation.class);
        for (Element mTypeElement : elements) {
            try {
                generateActivityFile(mTypeElement, "com.liu.test").writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public JavaFile generateActivityFile(Element mTypeElement, String packgeName) {

        StringBuffer mStringBuffer = new StringBuffer();
//        roundEnvironment.getElementsAnnotatedWith();
//        mTypeElement。
//        if (mTypeElement.getAnnotation(MyAnnotation.class) != null) {
//            Class<MyAnnotation> mMyAnnotation = (Class<MyAnnotation>) mTypeElement.getAnnotation(MyAnnotation.class).getClass();
//            Method[] methods = mMyAnnotation.getDeclaredMethods();//反射获取所有方法
//            for (Method method:methods){
//                if (method.isAnnotationPresent(MyAnnotation.class)){//注解接口 判断注解为UseCases类
//                    MyAnnotation sUseCases=  method.getAnnotation(MyAnnotation.class);
//                    System.out.println("====sUseCases>"+sUseCases.value());
//                    for (int mInt:sUseCases.value()){
//                        mStringBuffer.append(mInt).append(",");
//                    }
//                }
//            }
//        }
        MyAnnotation LRequest = mTypeElement.getAnnotation(MyAnnotation.class);
//        for (int mInt:LRequest.text()){
//            mStringBuffer.append(mInt).append(",");
//        }
//        mStringBuffer.append(LRequest.value());

        MethodSpec.Builder injectMethod = MethodSpec.methodBuilder(TypeUtil.METHOD_NAME)
                .addModifiers(Modifier.PUBLIC)
//                .addParameter(TypeName.get(mTypeElement.asType()), "activity", Modifier.FINAL)
                .addParameter(TypeName.get(mTypeElement.asType()), "activity", Modifier.FINAL);
        injectMethod.addStatement("android.widget.Toast.makeText" +
                "(activity, $S,android.widget.Toast.LENGTH_SHORT).show();", LRequest.text());
        //generaClass
        TypeSpec injectClass = TypeSpec.classBuilder(mTypeElement.getSimpleName() + "$$InjectActivity")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(injectMethod.build())
                .build();
//        String packgeName = mElements.getPackageOf(mTypeElement).getQualifiedName().toString();
        return JavaFile.builder(packgeName, injectClass).build();
    }
}

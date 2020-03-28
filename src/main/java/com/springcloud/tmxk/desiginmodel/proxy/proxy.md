一、什么是ASM

      首先看下官方中的说明 ASM a very small and fast Java bytecode manipulation framework。
    
      ASM是一个JAVA字节码分析、创建和修改的开源应用框架。它可以动态生成二进制格式的stub类或其他代理类，或者在类被JAVA虚拟机装入内存之前，动态修改类。在ASM中提供了诸多的API用于对类的内容进行字节码操作的方法。与传统的BCEL和SERL不同，在ASM中提供了更为优雅和灵活的操作字节码的方式。ASM相当小巧，并且它有更高的执行效率，是BCEL的7倍，SERP的11倍以上(摘自网络，具体没有测试)。目前ASM已被广泛的开源应用架构所使用，例如：Spring、Hibernate等。

二、ASM能做什么

      我们都知道，一般情况下，Class文件是通过javac编译器产生的，然后通过类加载器加载到虚拟机内，再通过执行引擎去执行。现在我们可以通过ASM的API直接生成符合Java虚拟机规范的Class字节流，这样，ASM做的事情一定程度上正是javac解释器做的工作。
    
     可以说ASM分析一个类、从字节码角度创建一个类、修改一个已经被编译过的类文件。
    
     那么，我们就可以通过ASM来实现诸如代码生成，代码混淆，代码转换等等以字节码为操作目标的工作

三、Java二进制(class)文件的格式

     要想驾驭ASM，先要了解一下JAVA的CLASS文件格式。JAVA的CLASS文件通常是树型结构。根节点包含以下元素：

ConstantPool：符号表；
FieldInfo：类中的成员变量信息；
MethodInfo：类中的方法描述；
Attribute：可选的附加节点。
      FieldInfo节点包含成员变量的名称，诸如public,private,static等的标志。


​             
```java
package com.sunchao.asm;

import java.io.File;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * the hello world byte code generate 
 * by asm.
 * @author Administrator
 *
 */
public class HelloWorld {
    
    public static void main(String args[]) throws Exception {
        ClassWriter classWriter = new ClassWriter(0);
        String className = "com/sunchao/asm/HelloWorld";
        classWriter.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC, className, null, 
                                          "java/lang/Object", null);
        
        
        MethodVisitor initVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
                                           "()V", null, null);
        initVisitor.visitCode();
        initVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        initVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", 
                                            "V()");
        initVisitor.visitInsn(Opcodes.RETURN);
        initVisitor.visitMaxs(1, 1);
        initVisitor.visitEnd();
        
        MethodVisitor helloVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "sayHello", 
                                           "()V;", null, null);
        helloVisitor.visitCode();
        helloVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", 
                                             "Ljava/io/PrintStream;");
        helloVisitor.visitLdcInsn("hello world!");
        helloVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", 
                                                "println", "(Ljava/lang/String;)V");
        helloVisitor.visitInsn(Opcodes.RETURN);
        helloVisitor.visitMaxs(1, 1);
        helloVisitor.visitEnd();
        
        classWriter.visitEnd();
        byte[] code = classWriter.toByteArray();
        File file = new File("D:\\HelloWorld.class");
        FileOutputStream output = new FileOutputStream(file);
        output.write(code);
        output.close();
    }

}
```


​      
​      
​      
​      
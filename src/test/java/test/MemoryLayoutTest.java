package test;

import com.github.model.TestObject;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author 康盼Java开发工程师
 */
public class MemoryLayoutTest {

    public static void main(String[] args){
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(TestObject.class).toPrintable());
    }
}



/**
 * test.A object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0    12        (object header)                           N/A
 *      12     4        (alignment/padding gap)
 *      16     8   long A.i                                       N/A
 * Instance size: 24 bytes
 * Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
 *
 *
    对象头(Object Header):

        从图片上得知对象头分为两部分：Mark Word 与 Class Pointer(类型指针)。

        Mark Word存储了对象的hashCode、GC信息、锁信息三部分，Class Pointer存储了指向类对象信息的指针。
        在32位JVM上对象头占用的大小是8字节，64位JVM则是16字节，两种类型的Mark Word 和 Class Pointer各占一半空间大小。

        在64位JVM上有一个压缩指针选项-XX:+UseCompressedOops，默认是开启的。
        开启之后Class Pointer部分就会压缩为4字节，此时对象头大小就会缩小到12字节。
 **/

package template;

import java.io.FileInputStream;  
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;  
import java.nio.MappedByteBuffer;  
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;  
  
public class MapMemoryBuffer {  
    
    
    public static void main(String[] args) throws IOException {
        
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.putInt(-1);
        byteBuffer.put((byte)2);
        byteBuffer.putChar('a');
        
        FileChannel fc = (FileChannel) Files.newByteChannel(Paths.get("E:/txt.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        byteBuffer.rewind();
        fc.write(byteBuffer);
        fc.close();
        
//        FileChannel fc2 = (FileChannel) Files.newByteChannel(Paths.get("E:/txt.txt"));
//        fc2.read(byteBuffer);
//        byteBuffer.rewind();
//        System.out.println(byteBuffer.getInt());
        
        
        
        
    }
  
//    public static void main(String[] args) throws Exception {  
//        ByteBuffer byteBuf = ByteBuffer.allocate(300 * 1024 * 1024);  
//        byte[] bbb = new byte[300 * 1024 * 1024];  
//        FileInputStream fis = new FileInputStream("e:/a.mp4");  
//        FileOutputStream fos = new FileOutputStream("e:/b.mp4");  
//        FileChannel fc = fis.getChannel();  
//        long t = System.currentTimeMillis();// 得到当前的时间  
//        
////        int count = fis.read(bbb);
////        fos.write(bbb, 0, count);
////        fos.flush();
//        
//        
////        ByteBuffer mappedByteBuffer = MappedByteBuffer.allocate(300 * 1024 * 1024);
////        
////        FileChannel infileChannel = null;
////        try {
////            
////            infileChannel = (FileChannel) Files.newByteChannel(
////                    Paths.get("e:/a.mp4"));
////            long channelSize = infileChannel.size();
////            mappedByteBuffer.
////            MappedByteBuffer mappedByteBuffer = infileChannel.map(MapMode.READ_ONLY, 0, channelSize);
////            for (int i = 0; i < channelSize; i ++)
////                System.out.print((char)mappedByteBuffer.get());
////            
////            
////        } catch (IOException e) {
////            e.printStackTrace();
////        } finally {
////            if ()
////        }
//        
//        ByteBuffer mappedByteBuffer2 = MappedByteBuffer.allocate(102);
//        
//        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
//        byteBuffer.putChar('a');
//        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(Paths.get("E:/a.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
//            for (int i = 0; i < 26; i ++)
//                byteBuffer.put((byte)('a' + i));
//            byteBuffer.rewind();
//            fileChannel.write(byteBuffer);
//        } catch (IOException e) {}
//        
//        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(Paths.get("E:/b.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
//            byteBuffer.rewind();
//            for (int i = 0; i < 26; i ++)
//                byteBuffer.put((byte)('A' + i));
//            byteBuffer.rewind();
//            fileChannel.write(byteBuffer);
//        } catch (IOException e) {}
//        
//        
//        
//        
//        System.out.println(System.currentTimeMillis() - t);
//        
//        
////        fc.read(byteBuf);// 1 读取  
//        //MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());  
////        System.out.println(fc.size()/1024);  
////        long timeEnd = System.currentTimeMillis();// 得到当前的时间  
////        System.out.println("Read time :" + (timeEnd - timeStar) + "ms");  
////        timeStar = System.currentTimeMillis();  
////        fos.write(bbb);//2.写入  
////        //mbb.flip();  
////        timeEnd = System.currentTimeMillis();  
////        System.out.println("Write time :" + (timeEnd - timeStar) + "ms");  
////        fos.flush();  
////        fc.close();  
////        fis.close();  
//    }  
  
}  
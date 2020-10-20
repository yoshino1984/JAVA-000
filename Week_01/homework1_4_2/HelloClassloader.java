package homework1_4_2;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class HelloClassloader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Object obj = new HelloClassloader().findClass("Hello").newInstance();
            obj.getClass().getMethod("hello").invoke(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        int[] fileBytes = readClassFile("Week_01/homework1_4_2/Hello.xlass");
        byte[] decodeBytes = decodeClassBytes(fileBytes);
        return defineClass(name, decodeBytes, 0, decodeBytes.length);
    }

    private byte[] decodeClassBytes(int[] fileBytes) {
        byte[] bytes = new byte[fileBytes.length];
        for (int i = 0; i < fileBytes.length; i++) {
            bytes[i] = (byte) (255 - fileBytes[i]);
        }
        return bytes;
    }

    private int[] readClassFile(String path) {
        File file = new File(path);
        List<Integer> bytes = new ArrayList<>();
        try (InputStream is = new FileInputStream(file)){
            int val;
            while ((val = is.read()) != -1) {
                bytes.add(val);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes.stream().mapToInt(Integer::byteValue).toArray();
    }
}

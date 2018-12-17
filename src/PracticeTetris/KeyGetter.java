package PracticeTetris;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class KeyGetter {

	private static HashMap<Integer, String> keys;

	public static void loadKeys() {
		keys = new HashMap<Integer, String>();
		Field[] field = KeyEvent.class.getFields(); 
		//Field�� �ٸ� Ŭ������ �ִ� �������� ������ �������ִ� Ŭ�����̴�. �����ڷ� : https://12bme.tistory.com/129
		for (Field f : field) {
			if (Modifier.isStatic(f.getModifiers())) { 
				//Modifier.isStatic �� Ŭ������ ����� ���� �ʵ常 �˻��մϴ�.
				//getModifiers�� �ش� ������ ������ ������ �����մϴ�.
				if (f.getName().startsWith("VK")) {
					//startswith StringŬ������ �޼ҵ��̸� ���ڷ� ���� Strign������ ���ξ �����ϴ��� Ȯ���ϴ� �޼ҵ��Դϴ�
					try {
						
						int num = f.getInt(null);
						String name = KeyEvent.getKeyText(f.getInt(null));
						keys.put(null, name);
						System.out.println(name);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}

package PracticeTetris;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class KeyGetter {

	public static HashMap<String, Integer> keys;
	public static ArrayList<String> keyNames;

	public static void loadKeys() {
		keys = new HashMap<String, Integer>();
		keyNames = new ArrayList<String>();
		Field[] field = KeyEvent.class.getFields();
		// Field�� �ٸ� Ŭ������ �ִ� �������� ������ �������ִ� Ŭ�����̴�. �����ڷ� : https://12bme.tistory.com/129
		for (Field f : field) {
			if (Modifier.isStatic(f.getModifiers())) {
				// Modifier.isStatic �� Ŭ������ ����� ���� �ʵ常 �˻��մϴ�.
				// getModifiers�� �ش� ������ ������ ������ �����մϴ�.
				if (f.getName().startsWith("VK")) {
					// startswith StringŬ������ �޼ҵ��̸� ���ڷ� ���� Strign������ ���ξ �����ϴ��� Ȯ���ϴ� �޼ҵ��Դϴ�
					try {

						int num = f.getInt(null);
						// getint�� ���� ������
						// https://www.tutorialspoint.com/javareflect/javareflect_field_getint.htm
						String name = KeyEvent.getKeyText(f.getInt(null));
						keys.put(name, num);
						keyNames.add(name);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}

package com.expedia.taxCalculator.collection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.expedia.taxCalculator.Item.Item;
import com.expedia.taxCalculator.config.ItemConfig;

public class ItemCollectionTest {

	@InjectMocks
	private ItemCollection collection;

	@Mock
	private ItemConfig config;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(config.initConnReadTimeoutMap()).thenReturn(new HashMap<String, String>() {
			{
				put("DISCOUNT", "17.5");
				put("BOOK", "true:0.0");
				put("PILLS", "false:0.0");
				put("SNACK", "true:0.0");
			}
		});
		try {
			Method postConstruct = ItemCollection.class.getDeclaredMethod("init", null);
			postConstruct.setAccessible(true);

			postConstruct.invoke(collection);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addToList() {
		List<Item> list=new ArrayList<>();
		String s = "1 book: 29.49";
		collection.addToList(s,list);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(1, list.get(0).getQuantity());
	}
	@Test
	public void addToListPills() {
		List<Item> list=new ArrayList<>();
		String s = "1 pills: 29.49";
		collection.addToList(s,list);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(1, list.get(0).getQuantity());
	}
	@Test
	public void addToListSnacks() {
		List<Item> list=new ArrayList<>();
		String s = "1 snack: 29.49";
		collection.addToList(s,list);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(1, list.get(0).getQuantity());
	}
	@Test
	public void addToListExceptionInType() {
		List<Item> list=new ArrayList<>();
		String s = "1 exception: 29.49";
		collection.addToList(s,list);
		Assert.assertNotNull(list);
		Assert.assertEquals(0, list.size());
	}
	@Test
	public void addToListAdditionalTaxInType() {
		Mockito.when(config.initConnReadTimeoutMap()).thenReturn(new HashMap<String, String>() {
			{
				put("DISCOUNT", "17.5");
				put("CD", "true:0.0:5.5");
			}
		});
		try {
			Method postConstruct = ItemCollection.class.getDeclaredMethod("init", null);
			postConstruct.setAccessible(true);

			postConstruct.invoke(collection);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		List<Item> list=new ArrayList<>();
		String s = "1 cd: 29.49";
		collection.addToList(s,list);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
	}
}

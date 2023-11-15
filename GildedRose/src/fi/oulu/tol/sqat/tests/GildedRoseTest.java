package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	
	@Test
	public void exampleTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	
	@Test 
	public void gildedRoseTest_TestInit() {
		GildedRose i = new GildedRose();
		
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
		
		GildedRose.init();
		String comp = outContent.toString();
		assertEquals("OMGHAI!\r\n", comp);
		
		List<Item> items = i.getItems();
		assertEquals(6, items.size());
		
		System.setOut(System.out);
	}
	
	@Test
	public void gildedRoseTest_TestDegredation() {
		GildedRose i = new GildedRose();
		Item regItem = new Item("Reg item", 5, 10);
		i.setItem(regItem);
		i.oneDay();
		
		assertEquals(9, regItem.getQuality());
		assertEquals(4, regItem.getSellIn());
	}
	
	@Test
	public void gildedRoseTest_TestDoubleDegredation() {
		
		GildedRose i = new GildedRose();
		Item regItem = new Item("Reg item", 1, 10);
		i.setItem(regItem);
		i.oneDay();
		i.oneDay();
		
		assertEquals(7, regItem.getQuality());
	}
	
	@Test
	public void gildedRoseTest_TestNegativeQuality() {
		GildedRose i = new GildedRose();
		Item regItem = new Item("Reg item", 1, 0);
		i.setItem(regItem);
		i.oneDay();
		i.oneDay();
		
		assertEquals(0, regItem.getQuality());
	}
	
	@Test 
	public void gildedRoseTest_TestAgedBrie() {
		GildedRose i = new GildedRose();
		Item agedBrie = new Item("Aged Brie", 2, 0);
		i.setItem(agedBrie);
		i.oneDay();
		
		assertEquals(1, agedBrie.getQuality());
	}
	
	@Test
	public void gildedRoseTest_TestMaxQuality() {
		GildedRose i = new GildedRose();
		Item agedBrie = new Item("Aged Brie", 2, 50);
		i.setItem(agedBrie);
		i.oneDay();
		i.oneDay();
		
		assertEquals(50, agedBrie.getQuality());
	}
	
	@Test 
	public void gildedRoseTest_Sulfuras() {
		GildedRose i = new GildedRose();
		Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 1, 80);
		i.setItem(sulfuras);
		i.oneDay();
		
		assertEquals(80, sulfuras.getQuality());
		assertEquals(1, sulfuras.getSellIn());
	}
	
	public void passDays(GildedRose inn, int days) {
		for(int i = 0; i < days; i++) {
			inn.oneDay();
		}
	}
	
	@Test
	public void gildedRoseTest_Backstage() {
		GildedRose i = new GildedRose();
		Item backstage = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
		i.setItem(backstage);
		
		//normal increase of 1/day
		passDays(i, 5);
		
		assertEquals(25, backstage.getQuality());
		
		//increase of 2/day
		passDays(i, 5);
		
		assertEquals(35, backstage.getQuality());
		
		//increase of 3/day
		passDays(i, 5);
		
		assertEquals(50, backstage.getQuality());
		
		//loses value
		i.oneDay();
		
		assertEquals(0, backstage.getQuality());
	}
	
	@Test
	public void gildedRoseTest_loopPassThrg2() {
		GildedRose i = new GildedRose();
		Item regItem1 = new Item("Regular item 1", 5, 30);
		Item regItem2 = new Item("Regular item 2", 5, 30);
		
		i.setItem(regItem1);
		i.setItem(regItem2);
		
		passDays(i, 2);
		
		
		assertEquals(28, regItem1.getQuality());
		assertEquals(28, regItem2.getQuality());
		
		assertEquals(3, regItem1.getSellIn());
		assertEquals(3, regItem2.getSellIn());
	}
	
	@Test
	public void gildedRoseTest_loopPassThrgM() {
		GildedRose i = new GildedRose();
		Item regItem1 = new Item("Regular item 1", 5, 30);
		Item regItem2 = new Item("Regular item 2", 5, 30);
		Item regItem3 = new Item("Regular item 3", 5, 30);
		
		i.setItem(regItem1);
		i.setItem(regItem2);
		i.setItem(regItem3);
		
		passDays(i, 3);
		
		
		assertEquals(27, regItem1.getQuality());
		assertEquals(27, regItem2.getQuality());
		assertEquals(27, regItem3.getQuality());
		
		assertEquals(2, regItem1.getSellIn());
		assertEquals(2, regItem2.getSellIn());
		assertEquals(2, regItem3.getSellIn());
	}
	
	@Test
	public void gildedRoseTest_loopPassThrgNplus1() {
		GildedRose i = new GildedRose();
		Item regItem1 = new Item("Regular item 1", 10, 30);
		Item regItem2 = new Item("Regular item 2", 10, 30);
		Item regItem3 = new Item("Regular item 3", 10, 30);
		
		i.setItem(regItem1);
		i.setItem(regItem2);
		i.setItem(regItem3);
		
		passDays(i, 5);
		
		assertEquals(25, regItem1.getQuality());
		assertEquals(25, regItem2.getQuality());
		assertEquals(25, regItem3.getQuality());
		
		assertEquals(5, regItem1.getSellIn());
		assertEquals(5, regItem2.getSellIn());
		assertEquals(5, regItem3.getSellIn());
		
		i.oneDay();
		
		assertEquals(24, regItem1.getQuality());
		assertEquals(24, regItem2.getQuality());
		assertEquals(24, regItem3.getQuality());
		
		assertEquals(4, regItem1.getSellIn());
		assertEquals(4, regItem2.getSellIn());
		assertEquals(4, regItem3.getSellIn());
		
		i.oneDay();
		
		assertEquals(23, regItem1.getQuality());
		assertEquals(23, regItem2.getQuality());
		assertEquals(23, regItem3.getQuality());
		
		assertEquals(3, regItem1.getSellIn());
		assertEquals(3, regItem2.getSellIn());
		assertEquals(3, regItem3.getSellIn());
	}
	
	@Test
	public void gildedRoseTest_killingSurvivors1() {
		GildedRose i = new GildedRose();
		GildedRose.init();
		
		List<Item> items = i.getItems();
		assertEquals(19, items.get(0).getQuality());
	}
	
	@Test
	public void gildedRoseTest_killingSurvivors2() {
		GildedRose i = new GildedRose();
		Item regItem1 = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
		i.setItem(regItem1);
		
		i.oneDay();
		
		assertEquals(50, regItem1.getQuality());

	}
	
	@Test
	public void gildedRoseTest_gettingCoverage1() {
		GildedRose i = new GildedRose();
		Item regItem1 = new Item("Aged Brie", -1, 10);
		i.setItem(regItem1);
		
		i.oneDay();
		
		assertEquals(12, regItem1.getQuality());
	}
	
	@Test
	public void gildedRoseTest_gettingCoverage2() {
		GildedRose i = new GildedRose();
		Item regItem1 = new Item("Aged Brie", -1, 49);
		i.setItem(regItem1);
		
		i.oneDay();
		
		assertEquals(50, regItem1.getQuality());
	}
	
	
	
}

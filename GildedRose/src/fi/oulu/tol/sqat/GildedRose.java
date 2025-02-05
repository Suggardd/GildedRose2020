package fi.oulu.tol.sqat;

import java.util.ArrayList;
import java.util.List;


public class GildedRose {

	private static List<Item> items = null;


	public static void main(String[] args) {
		init();
	}
	
    public static void init() {
        System.out.println("OMGHAI!");
        items = initItems();
        updateQuality();
    }
	
	public static List<Item> initItems() {
		List<Item> initItems = new ArrayList<>();
		initItems.add(new Item("+5 Dexterity Vest", 10, 20));
		initItems.add(new Item("Aged Brie", 2, 0));
		initItems.add(new Item("Elixir of the Mongoose", 5, 7));
		initItems.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		initItems.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		initItems.add(new Item("Conjured Mana Cake", 3, 6));
		return initItems;
	}

	
    public static void updateQuality()
    {
        for (int i = 0; i < items.size(); i++)
        {
            if ((!"Aged Brie".equals(items.get(i).getName())) && !"Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getName())) 
            {
                if (items.get(i).getQuality() > 0)
                {
                    if (!"Sulfuras, Hand of Ragnaros".equals(items.get(i).getName()))
                    {
                        items.get(i).setQuality(items.get(i).getQuality() - 1);
                    }
                }
            }
            else
            {
                if (items.get(i).getQuality() < 50)
                {
                    items.get(i).setQuality(items.get(i).getQuality() + 1);

                    if ("Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getName()))
                    {
                        if (items.get(i).getSellIn() < 11)
                        {
                            if (items.get(i).getQuality() < 50)
                            {
                                items.get(i).setQuality(items.get(i).getQuality() + 1);
                            }
                        }

                        if (items.get(i).getSellIn() < 6)
                        {
                            if (items.get(i).getQuality() < 50)
                            {
                                items.get(i).setQuality(items.get(i).getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (!"Sulfuras, Hand of Ragnaros".equals(items.get(i).getName()))
            {
                items.get(i).setSellIn(items.get(i).getSellIn() - 1);
            }

            if (items.get(i).getSellIn() < 0)
            {
                if (!"Aged Brie".equals(items.get(i).getName()))
                {
                    if (!"Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getName()))
                    {
                        if (items.get(i).getQuality() > 0)
                        {
                            if (!"Sulfuras, Hand of Ragnaros".equals(items.get(i).getName()))
                            {
                                items.get(i).setQuality(items.get(i).getQuality() - 1);
                            }
                        }
                    }
                    else
                    {
                        items.get(i).setQuality(items.get(i).getQuality() - items.get(i).getQuality());
                    }
                }
                else
                {
                    if (items.get(i).getQuality() < 50)
                    {
                        items.get(i).setQuality(items.get(i).getQuality() + 1);
                    }
                }
            }
        }
    }

    //constructor
    public GildedRose() {
    	items = new ArrayList<Item>();
    }
    
    //getter
    public List<Item> getItems() {
    	return items;
    }
    //setter
    public void setItem(Item item) {
    	items.add(item);
    }
    
    //update one day
    public void oneDay() {
    	updateQuality();
    }
}

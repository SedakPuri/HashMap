
public class Driver 
{
	public static void main(String[] args)
	{
		HashMap<Integer, Double> map = new HashMap<>();
		
		for(int i=0; i < 1000000; i++)
		{
			map.put(i, i*2.0);
		}
		System.out.println("Map:");
		System.out.println(map);
		
	}

}

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;


public class HashMap<K, V> implements Map<K, V>
{
	
	private LinkedList<MapEntry<K, V>>[] theMap;
	private int size;
	
	private int N;
	private int p;
	private int a;
	private int b;
	
	public HashMap()
	{
		initHashMap(31);
		
		theMap = new LinkedList[N];
		
		for(int i=0; i < N; i++)
		{
			theMap[i] = new LinkedList<>();
		}
	}
	
	private void initHashMap(int newSize)
	{
		size = 0;
		N = ((new BigInteger(Integer.toString(newSize))).nextProbablePrime()).intValue();;
		p = ((new BigInteger(Integer.toString(N))).nextProbablePrime()).intValue();
		System.out.println("prime: " + p);
		a = (int) (Math.random() * p-1) + 1;
		b = (int) (Math.random() * p);		
	}
	
	
	private int bucket(K key)
	{
		return ((Math.abs(key.hashCode() * a + b)) % p) % N;
	}
	
	
	public V put(K key, V value) 
	{
		int index = bucket(key);
		for(MapEntry<K, V> e : theMap[index])
		{
			if(e.key.equals(key))
			{
				V oldValue = e.value;
				e.value = value;
				return oldValue;
			}
		}
		theMap[index].add(new MapEntry<>(key, value));
		size++;
		expandIfNeeded();
		return null;
	}

	private void expandIfNeeded()
	{
		if(size/theMap.length > 0.75)
		{
			//expand
			Iterable<Entry<K, V>> entries = this.entrySet();
			this.initHashMap(size * 2);
			theMap = new LinkedList[N];
			for(int i=0; i < N; i++)
			{
				theMap[i] = new LinkedList<>();
			}				

			for(Entry<K, V> e : entries)
			{
				this.put(e.getKey(), e.getValue());
			}
		}
	}
	@Override
	public V set(K key, V newValue) 
	{
		int index = bucket(key);
		for(MapEntry<K,V> e : theMap[index])
		{
			if(e.key.equals(key))
			{
				V oldValue = e.value;
				e.value = newValue;
				return oldValue;
			}
		}
		return null;
	}

	public V get(K key) 
	{
		int index = bucket(key);
		
		//Traverse using iterator
		Iterator<MapEntry<K, V>> it = theMap[index].iterator();
		
		while(it.hasNext())
		{
			MapEntry<K,V> e = it.next();
			if(e.getKey().equals(key))
			{
				return e.getValue();
			}
		}
		return null;
	}

	public V remove(K key) 
	{
		int index = bucket(key);
		LinkedList<MapEntry<K, V>> bucket = theMap[index];
		
		//Traverse the linkedlist
		for(int i=0; i < bucket.size(); i++)
		{
			if(bucket.get(i).key.equals(key))
			{
				V old = bucket.get(i).value;
				bucket.remove(i);
				return old;
			}
		}
		return null;
	}

	@Override
	public int size() 
	{
		return size;
	}

	@Override
	public boolean isEmpty() 
	{
		return size == 0;
	}

	public Iterable<K> keySet() 
	{
		ArrayList<K> keys = new ArrayList<>();
		for(int i=0; i < N; i++)
		{
			LinkedList<MapEntry<K, V>> bucket = theMap[i];
			for(MapEntry<K, V> e : bucket)
			{
				keys.add(e.key);
			}
		}
		return keys;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < theMap.length; i++)
		{
			sb.append(i + ": [");
			for(MapEntry<K, V> entry : theMap[i])
			{
				sb.append(entry.toString() + " ");
			}
			sb.append("]\n");
		}
		return sb.toString();
	}

	@Override
	public Iterable<V> valueSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() 
	{
		ArrayList<Entry<K, V>> entries = new ArrayList<>();
		for(int i=0; i < theMap.length; i++)
		{
			for(MapEntry e : theMap[i])
			{
				entries.add(e);
			}
		}
		return entries;
	}
	
	private static class MapEntry<K1, V1> implements Entry<K1, V1>
	{
		K1 key;
		V1 value;
		
		public MapEntry(K1 k, V1 v)
		{
			key = k;
			value = v;
		}
		
		public String toString()
		{
			return "(" + key + ", " + value +")";
		}
		public K1 getKey() 
		{
			return key;
		}

		
		public V1 getValue() 
		{
			return value;
		}

		public V1 setValue(V1 v) 
		{
			V1 old = value;
			this.value = v;
					
			return old;
		}	
		
		public String toString()
		{
			return "(" + key.toString() + ": " + value.toString() + ")"; 
		}
	}
	
	
}

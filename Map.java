import java.util.Map.Entry;

public interface Map<K, V> 
{
	public V put(K key, V value);
	public V set(K key, V value);
	public V get(K key);
	public V remove(K key);
	public int size();
	public boolean isEmpty();
	public Iterable<K> keySet();
	public Iterable<V> valueSet();
	public Iterable<Entry<K, V>> entrySet();
}

package com.xiji.cashloan.cl.util.token;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: king
 * @Date: 2018/11/16 11:29
 * @Description:
 */
public class LRU<K,V> extends LinkedHashMap<K, V> implements Map<K, V> {
    private static final long serialVersionUID = 1L;
    private static int rmSize = 10000;
    public LRU(int initialCapacity,
        float loadFactor,
        boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }
    public LRU(int initialCapacity,
        float loadFactor,
        boolean accessOrder,int rmSizeinit) {
        super(initialCapacity,loadFactor, accessOrder);
        rmSize = rmSizeinit;
    }

    /**
     * @description 重写LinkedHashMap中的removeEldestEntry方法，当LRU中元素多余6个时，
     *              删除最不经常使用的元素
     * @param eldest
     * @return
     * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
     */
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        // TODO Auto-generated method stub
        if(size() > rmSize){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        LRU<Character, Integer> lru = new LRU<Character, Integer>(
            16, 0.75f, true,3);

        String s = "abcdefghijkl";
        for (int i = 0; i < s.length(); i++) {
            lru.put(s.charAt(i), i);
        }
        System.out.println("LRU中key为h的Entry的值为： " + lru.get('h'));
        System.out.println("LRU的大小 ：" + lru.size());
        System.out.println("LRU ：" + lru);
    }
}

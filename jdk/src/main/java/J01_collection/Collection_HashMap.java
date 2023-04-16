package J01_collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Collection_HashMap {

    @Test
    public void test(){
        Map<String,String> map = new HashMap<>();

        map.put(null,"0");


    }

   /* public V put(K key, V value) {
        //传入一个元素key、value，此时调用hash（key）产生hash值
        return putVal(hash(key), key, value, false, true);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;     //tab[]为数组，p是每个桶
 ①      if ((tab = table) == null || (n = tab.length) == 0) //第一步，table为空，则调用resize()函数创建一个，n为初始长度
            n = (tab = resize()).length;
 ②      if ((p = tab[i = (n - 1) & hash]) == null)   //第二步，计算元素所要储存的位置index,并对null做出处理。（即要存储的数组下标处为null，则创建一个node元素处理）
            //注意这里，如果tab[i]==null，说明这个位置上没有元素，这个时候就创建一个新的Node元素
            tab[i] = newNode(hash, key, value, null);
        else {  //else,否则，也就是，这个要添加的位置上面已经有元素了，也就是发生了碰撞。这个时候就要具体情况分
            //分类讨论：1.key值相同，直接覆盖 2.链表已经超过了8位，变成了红黑树 3.链表是正常的链表
            Node<K,V> e; K k;
            if (p.hash == hash &&   //如果节点key存在，则覆盖原来位置的key（此时key填充完）
                    ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;   //将p链先赋给e
      ③        else if (p instanceof TreeNode) //第三步，判断该链是否为红黑树，是则调用用红黑树对应的put方法
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {    //最后则tab和p不为空，且链不是红黑树，即链为单向链表
                for (int binCount = 0; ; ++binCount) {//循环计算该链表的长度
                    if ((e = p.next) == null) {//如果p.next为null（即循环到p链的最后一个元素了），则直接将传进来的接到最后一个的next去
                        p.next = newNode(hash, key, value, null);
                        //链表长度大于8转换为红黑树（调用相应方法）
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    //如果节点key存在，则覆盖原来位置的key，同时将原来位置的元素，沿着链表向后移一位（这里是先用e记录该节点然后在下面进行value值的替换）
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;//将p.next传给下一次循环的p，即向下循环p链
                }
            }
            if (e != null) { // existing mapping for key//这里进行上面记录的相同key的节点的value值的替换
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
      ④         resize();                                       //第四步：超过最大容量限制，扩容
        afterNodeInsertion(evict);
        return null;
    }*/
}

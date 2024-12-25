package com.example.demo.study.demo2412;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Demo1224 {
    public static void main(String[] args) {
//        Trie trie = new Trie();
//        trie.insert("apple");
//        boolean apple = trie.search("apple");
//        boolean app = trie.search("app");
//        boolean app1 = trie.startsWith("app");
//        trie.insert("app");
//        boolean app2 = trie.search("app");
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("a");
        wordDictionary.addWord("ab");
        boolean a = wordDictionary.search("a");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        boolean pad = wordDictionary.search("pad");
        boolean bad = wordDictionary.search("bad");
        boolean bad1 = wordDictionary.search("b.d");
        boolean bad2 = wordDictionary.search("b..");
        System.out.println();
    }
}


// 208
/*
Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false

示例：

输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]

解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True


提示：
1 <= word.length, prefix.length <= 2000
word 和 prefix 仅由小写英文字母组成
insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 */

class Trie {
    private HashSet<String> word;
    private HashSet<String> prefix;

    public Trie() {
        this.word = new HashSet<>();
        this.prefix = new HashSet<>();
    }

    public void insert(String word) {
        if (this.word.contains(word)) {
            return;
        }
        this.word.add(word);
        for (int i = word.length(); i >= 0; i--) {
            String substring = word.substring(0, i);
            if (!this.prefix.contains(substring)) {
                this.prefix.add(substring);
            }
        }
    }

    public boolean search(String word) {
        return this.word.contains(word);
    }

    public boolean startsWith(String prefix) {
        return this.prefix.contains(prefix);
    }
}

//211
/*
请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。

实现词典类 WordDictionary ：

WordDictionary() 初始化词典对象
void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。

示例：

输入：
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
输出：
[null,null,null,null,false,true,true,true]


//["WordDictionary","addWord","addWord","search","search","search","search","search","search","search","search"]
//[[],               ["a"],    ["ab"],    ["a"],  ["a."],  ["ab"],  [".a"],  [".b"],  ["ab."],  ["."],  [".."]]

//[null,             null,      null,      false,   true,   true,   false,    true,     false,  false,   true]
//[null,             null,       null,     true,     true,  true,    false,   true,     false,   true,   true]

解释：
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // 返回 False
wordDictionary.search("bad"); // 返回 True
wordDictionary.search(".ad"); // 返回 True
wordDictionary.search("b.."); // 返回 True

1 <= word.length <= 25
addWord 中的 word 由小写英文字母组成
search 中的 word 由 '.' 或小写英文字母组成
最多调用 104 次 addWord 和 search

 */


class WordDictionaryNode {
    public boolean isEnd;
    public WordDictionaryNode[] next;

    public WordDictionaryNode() {
        this.isEnd = false;
        this.next = new WordDictionaryNode[27];
    }
}

class tuple {
    public WordDictionaryNode node;
    public int nextIndex;

    public tuple(WordDictionaryNode node, int nextIndex) {
        this.node = node;
        this.nextIndex = nextIndex;
    }
}

class WordDictionary {
    private WordDictionaryNode head;

    public WordDictionary() {
        this.head = new WordDictionaryNode();
    }

    public void addWord(String word) {
        char[] wordChars = word.toCharArray();
        ArrayDeque<tuple> wordDictionaryNodes = new ArrayDeque<>();

        this.head.next[wordChars[0] - 'a'] = this.head.next[wordChars[0] - 'a'] == null ? new WordDictionaryNode() : this.head.next[wordChars[0] - 'a'];
        wordDictionaryNodes.add(new tuple(this.head.next[wordChars[0] - 'a'], 1));

        this.head.next[26] = this.head.next[26] == null ? new WordDictionaryNode() : this.head.next[26];
        wordDictionaryNodes.add(new tuple(this.head.next[26], 1));

        while (!wordDictionaryNodes.isEmpty()) {

            tuple poll = wordDictionaryNodes.poll();
            if (poll.nextIndex == word.length()) {
                poll.node.isEnd = true;
                continue;
            }
            int i = wordChars[poll.nextIndex] - 'a';

            if (poll.node.next == null) {
                poll.node.next = new WordDictionaryNode[27];
            }
            poll.node.next[i] = poll.node.next[i] == null ? new WordDictionaryNode() : poll.node.next[i];
            poll.node.next[26] = poll.node.next[26] == null ? new WordDictionaryNode() : poll.node.next[26];

            wordDictionaryNodes.add(new tuple(poll.node.next[i], poll.nextIndex + 1));
            wordDictionaryNodes.add(new tuple(poll.node.next[26], poll.nextIndex + 1));
        }
    }

    public boolean search(String word) {
        char[] wordChars = word.toCharArray();
        WordDictionaryNode curr = this.head;
        for (int i = 0; i < wordChars.length; i++) {
            int index = wordChars[i] - 'a';
            if (wordChars[i] == '.') {
                index = 26;
            }
            if (curr.next[index] == null) {
                return false;
            }
            curr = curr.next[index];

            if (i == wordChars.length - 1 && !curr.isEnd) {
                return false;
            }
        }

        return true;
    }
}


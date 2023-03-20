package com.cn.jmw.trie;

public class TrieNodePath {

	private TrieNode node;
	private TrieNodePath parent;

	public TrieNode getNode() {
		return node;
	}

	public void setNode(TrieNode node) {
		this.node = node;
	}

	public TrieNodePath getParent() {
		return parent;
	}

	public void setParent(TrieNodePath parent) {
		this.parent = parent;
	}

}

package com.cn.jmw.controller;

import com.cn.jmw.dto.ResponseData;
import com.cn.jmw.service.TireService;
import com.cn.jmw.trie.entity.MultiCodeMode;
import com.cn.jmw.trie.entity.TriePrefixQueryResult;
import com.cn.jmw.trie.entity.TrieQueryResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年03月20日 16:31
 * @Version 1.0
 */
@RestController
@RequestMapping("Tire")
@Tag(name = "前缀树操作")
public class TireController {

    private final TireService tireService;

    public TireController(TireService tireService) {
        this.tireService = tireService;
    }

    public boolean add(int[] word, MultiCodeMode mode, int code, int type) {
        return tireService.add(word, mode, code, type);
    }

    public boolean add(int[] word, MultiCodeMode mode, int code) {
        return tireService.add(word, mode, code);
    }

    public boolean add(int[] word, MultiCodeMode mode) {
        return tireService.add(word, mode);
    }

    public boolean add(String word, MultiCodeMode mode, int code, int type) {
        return tireService.add(word, mode, code, type);
    }

    public boolean add(String word, MultiCodeMode mode, int code) {
        return tireService.add(word, mode, code);
    }

    @Operation(summary = "字符串添加")
    @PostMapping("/add")
    public ResponseData<Boolean> add(String word, MultiCodeMode mode) {
        return ResponseData.success(tireService.add(word, mode));
    }

    @Operation(summary = "单词第一个匹配信息查询")
    @PostMapping("/query")
    public ResponseData<TrieQueryResult> get(String word) {
        return ResponseData.success(tireService.get(word));
    }

    @Operation(summary = "单词全部匹配信息查询")
    @PostMapping("/queryAll")
    public ResponseData<List<TrieQueryResult>> getAll(String word) {
        return ResponseData.success(tireService.getAll(word));
    }

    @Operation(summary = "单词数量查询")
    @PostMapping("/size")
    public ResponseData<Integer> getSize() {
        return ResponseData.success(tireService.getSize());
    }

    @Operation(summary = "单词前缀查询,查询子树")
    @PostMapping("/prefix/query")
    public ResponseData<TriePrefixQueryResult> getPrefix(String word) {
        return ResponseData.success(tireService.getPrefix(word));
    }

    @Operation(summary = "移除单词")
    @PostMapping("/remove")
    public ResponseData<Boolean> remove(String word, int code, int type) {
        return ResponseData.success(tireService.remove(word, code, type));
    }

    public boolean remove(int[] word, int code, int type) {
        return tireService.remove(word,code,type);
    }

    @Operation(summary = "获取子树（辅助树）深度")
    @PostMapping("/deep")
    public ResponseData<Integer> getDeep(String word) {
        return ResponseData.success(tireService.getDeep(word));
    }

    public void clear() {
        tireService.clear();
    }

}

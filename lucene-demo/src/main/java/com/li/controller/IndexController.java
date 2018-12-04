package com.li.controller;/**
 * @author lihaodong
 * @create 2018-12-04 10:39
 * @desc
 **/

import com.li.lucene.ChineseSearch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**

 * @author lihaodong
 * @create 2018-12-04 10:39
 * @mail lihaodongmail@163.com
 * @desc
 **/

@Controller
@RequestMapping("/lucene")
public class IndexController {

    @GetMapping("/test/{keyWord}")
    public String test(@PathVariable("keyWord") String keyWord, Model model) {
        // 索引所在的目录
        String indexDir = "/Users/lihaodong/Desktop/lucene";
        // 要查询的字符
        try {
            List<String> list = ChineseSearch.search(indexDir, keyWord);
            model.addAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}

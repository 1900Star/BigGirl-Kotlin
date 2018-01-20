package com.yibao.gankkotlin.factroy

import android.annotation.TargetApi
import android.os.Build
import net.dongliu.requests.Requests
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.util.*


/**
 *  @项目名：  MyKotlin
 *  @包名：    com.yibao.gankkotlin.factroy
 *  @文件名:   MeiziTus
 *  @author:   Stran
 *  @Email:   www.strangermy@outlook.com / www.stranger98@gmail.com
 *  @创建时间:  2018/1/15 16:08
 *  @描述：    {TODO}
 */
class MeiziTus {

    fun main() {
        MeiziTus()
    }

    // 根地址
    var url = "http://www.meizitu.com/index.html"

    // 初始化爬取
    init {
        this.boot(this.url)
    }

    /**
     * 入口 ,启动爬虫
     */
    private fun boot(url: String) {

        // 拿到首页的HTML
        val html = this.soup(url)

        // 得到所有的标签 ,也就是分类
        val tags = html.select(".tags a")

        /**
         * 忽略一切异常 ,遍历所有的分类并保存图片
         */
        for (tag in tags) {
            try {
                // 得到页数的链接
                val pagesLink = this.getPagesUrls(tag.attr("href"))
                // 遍历每一页
                for (pageLink in pagesLink) {
                    try {
                        // 得到所有的链接并遍历和保存
                        val detailUrls = this.getDetailUrl(pageLink)
                        for (detail in detailUrls) {
                            try {
                                // 保存图片
                                this.getImgs(detail)
                            } catch (e: Exception) {
                                continue
                            }
                        }
                    } catch (e: Exception) {
                        continue
                    }
                }
            } catch (e: Exception) {
                continue
            }
        }
    }


    /**
     * 得到所有的图片并保存
     */
    @TargetApi(Build.VERSION_CODES.O)
    private fun getImgs(detail: MutableMap.MutableEntry<String, String>) {

        // 选中所有图片
        val imgs = this.soup(detail.value).select("#picture img")

        // 以标题为目录名
        val filePath = "/opt/imgs/" + detail.key

        // 建立文件夹
        File(filePath).mkdir()

        // 声明一个FLAG ,用于图片名
        var flag = 0
        for (img in imgs) {

            // 利用Requests保存图片
            Requests.get(img.attr("src"))
                    .send()
                    .toFileResponse(File(filePath + "/" + flag.toString() + ".jpg").toPath())

            // 保存一张 ,给flag++
            flag++

            // 打印提示到控制台
            println("""${detail.key} 中的第 $flag 张妹子图 : ${img.attr("src")}""".trimIndent())
        }
    }

    /**
     * 得到一页的详情链接
     */
    private fun getDetailUrl(url: String): TreeMap<String, String> {

        // 定义结果集
        val res = TreeMap<String, String>()

        // 选中瀑布流的URL标记
        val urls = this.soup(url).select(".wp-item .pic a")

        // 遍历所有URL拿到目录
        for (url in urls) {

            // 添加到结果集中
            res.put(url
                    .select("img")
                    .attr("alt")
                    .replace("<b>", "") // 删除加粗标题的B元素
                    .replace("</b>", ""), url.attr("href"))
        }
        return res
    }

    /**
     * 获得某分类的总页数并返回所有的链接
     */
    private fun getPagesUrls(url: String): Set<String> {

        // 结果集
        val res: MutableList<String> = mutableListOf()

        // 根URL
        val baseUrl = "http://www.meizitu.com/a/"

        // 拿到所有的页数
        val pages = this.soup(url).select("#wp_page_numbers li a")

        for (page in pages) {

            // 过滤无用的链接
            if (page.attr("href") == "下一页" ||
                    page.attr("href") == "末页" ||
                    page.attr("href") == "首页") {
                continue
            }

            // 插入第一页的链接
            res.add(baseUrl + page.attr("href").substring(0, page.attr("href").length - 6) + "1.html")

            // 添加URL到结果集
            res.add(baseUrl + page.attr("href"))
        }
        // URL去重
        return res.toSet()
    }

    /**
     * 获得soup对象
     */
    private fun soup(url: String): Document {


        /**
         * 利用指定的Header链接到URL ,并拉取资源
         */
        return Jsoup.connect(url)
                .headers(mapOf("User-Agent" to "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3298.3 Safari/537.36",
                        "Host" to "www.meizitu.com"
                )).get()
    }
}
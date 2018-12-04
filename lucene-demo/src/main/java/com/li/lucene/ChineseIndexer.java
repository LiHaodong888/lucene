package com.li.lucene;/**
 * @author lihaodong
 * @create 2018-12-04 10:32
 * @desc
 **/

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;

/**
 * @author lihaodong
 * @create 2018-12-04 10:32
 * @mail lihaodongmail@163.com
 * @desc
 **/

public class ChineseIndexer {
    /**
     * 存放索引的位置
     */
    private Directory dir;

    //准备一下用来测试的数据
    //用来标识文档
    private Integer ids[] = {1, 2, 3};

    private String citys[] = {"上海", "南京", "北京"};

    private String descs[] = {"上海总面积6340平方公里，辖16个市辖区，属亚热带季风性气候 [12]  。上海GDP居中国城市第一位，亚洲城市第二位。上海是全球著名的金融中心，全球人口规模和面积最大的都会区之一，被GaWC评为世界一线城市第六位 [13]  。上海“国家中心城市指数”居中国第二，住户存款总额和人均住户存款均居全国第二 [14-15]  。2017年，上海高新技术企业总数达到7642家 [16]  ，位列福布斯“中国大陆最佳商业城市排行榜”第一位。 [17] ",
            "南京是中国四大古都、首批国家历史文化名城 [11]  ，是中华文明的重要发祥地 [12]  ，历史上曾数次庇佑华夏之正朔 [13-14]  ，是四大古都中唯一未做过异族政权首都的古都 [15]  ，长期是中国南方的政治、经济、文化中心 [16]  。南京早在100-120万年前就有古人类活动，35-60万年前已有南京猿人在汤山生活，有着7000多年文明史、近2600年建城史和近500年的建都史，有“六朝古都”、“十朝都会”之称。 [17-20] ",
            "北京是首批国家历史文化名城和世界上拥有世界文化遗产数最多的城市，三千多年的历史孕育了故宫、天坛、八达岭长城、颐和园等众多名胜古迹。早在七十万年前，北京周口店地区就出现了原始人群部落“北京人”。公元前1045年，北京成为蓟、燕等诸侯国的都城。公元938年以来，北京先后成为辽陪都、金中都、元大都、明、清国都。明清时期，北京成为“天下四聚”之一，中华民国时期曾是北洋政府首都，1949年10月1日成为中华人民共和国首都。"};

    /**
     * 生成索引
     *
     * @param indexDir
     * @throws Exception
     */
    public void index(String indexDir) throws Exception {
        dir = FSDirectory.open(Paths.get(indexDir));
        // 先调用 getWriter 获取IndexWriter对象
        IndexWriter writer = getWriter();
        for (int i = 0; i < ids.length; i++) {
            Document doc = new Document();
            // 把上面的数据都生成索引，分别用id、city和desc来标识
            doc.add(new IntField("id", ids[i], Field.Store.YES));
            doc.add(new StringField("city", citys[i], Field.Store.YES));
            doc.add(new TextField("desc", descs[i], Field.Store.YES));
            //添加文档
            writer.addDocument(doc);
        }
        //close了才真正写到文档中
        writer.close();
    }

    /**
     * 获取IndexWriter实例
     *
     * @return IndexWriter
     * @throws Exception
     */
    private IndexWriter getWriter() throws Exception {
        //使用中文分词器
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        //将中文分词器配到写索引的配置中
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        //实例化写索引对象
        IndexWriter writer = new IndexWriter(dir, config);
        return writer;
    }

    public static void main(String[] args) throws Exception {
        // 索引存放路径
        new ChineseIndexer().index("/Users/lihaodong/Desktop/lucene");
    }


}


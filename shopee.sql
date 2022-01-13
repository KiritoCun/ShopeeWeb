-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: shopee2
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `sell_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cmnd` int DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `link_image` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`sell_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'Nguyễn Lan Anh',187671411,'0934878811','Nam Đàn, Nghệ An','admin001','matkhau001','https://kenh14cdn.com/2020/10/12/3a7e4050-4f5d-4516-b9d7-1ec600e2d404-16025053985191348178238.jpeg'),(2,'Lê Hải Nhiên',187671422,'09348788922','Tương Dương, Nghệ An','admin002','matkhau002','https://newsmd1fr.keeng.net/tiin/archive/images/20191025/085250_ban_gai_quang_hai_4.jpg'),(3,'Phạm Thị Quỳnh',187671433,'0934878833','Đô Lương, Nghệ An','admin003','matkhau003','https://chiase24.com/wp-content/uploads/2019/07/Girl-xinh-cute.jpg'),(4,'Nene',187671444,'0934878844','TP.Vinh, Nghệ An','admin004','matkhau004','https://kenh14cdn.com/2019/7/12/screen-shot-2019-07-12-at-004132-1562867159446886176865.png'),(5,'Địch Lệ Nhiệt Ba',187671455,'0934878855','Hưng Nguyên, Nghệ An','admin005','matkhau005','https://tiengtrung.com/wp-content/uploads/2020/11/dinh-le-nhiet-ba-nu-dien-vien-trung-quoc.jpg'),(6,'Triệu Lệ Dĩnh',187671466,'0934878866','Nghi Lộc, Nghệ An','admin006','matkhau006','https://media.travelmag.vn/files/content/2021/05/18/trieu-le-dinh-va-cuoc-song-hau-ly-hon6-09474330.jpg'),(9,NULL,143894739,NULL,NULL,'linhvo010299','123456',NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Váy ngắn'),(2,'Đầm'),(3,'Đồ liền thân'),(4,'Đồ truyền thống');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `category_id` int DEFAULT NULL,
  `sell_id` int DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price_old` int DEFAULT NULL,
  `price_current` int DEFAULT NULL,
  `sold` int DEFAULT NULL,
  `province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sale_off` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FK_category_id` (`category_id`),
  KEY `FK_sell_id` (`sell_id`),
  CONSTRAINT `FK_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `FK_sell_id` FOREIGN KEY (`sell_id`) REFERENCES `account` (`sell_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,2,6,'https://cf.shopee.vn/file/5ca743da04c520f2fc106b7c1024aca1_tn','Đầm Sơ Mi Váy Dáng Suông Gọn Người Che Khuyết Điểm Thoải Mái Dễ Chịu Khi Mặc',250000,220000,380,'Nghệ An',15),(2,2,6,'https://cf.shopee.vn/file/b400c8bcf6e987377ca96fef3d2db47d_tn','Đầm Tay Phồng Màu Trắng Phong Cách Retro Pháp',150000,83000,44,'Nghệ An',45),(3,1,5,'https://cf.shopee.vn/file/8f89d39576428bc5a52ceab9d5308155_tn','Váy trắng trễ vai, đầm trắng trễ vai vai bèo kèm ảnh thật',130000,88000,123,'TP. Hồ Chí Minh',32),(4,2,4,'https://cf.shopee.vn/file/f4912a77d563c45247ab8db3f3e7496a_tn','ĐẦM XÒE DỰ TIỆC MÀU ĐỎ TAY PHỐI LƯỚI SAO HOT HOT',150000,119000,47,'TP. Hồ Chí Minh',20),(5,3,6,'https://cf.shopee.vn/file/3d302c4a8c6846929f11fa20cd7047f1_tn','(Hàng Có Sẵn) Áo Sơ Mi Nữ Vải Chiffon Dài Tay',164000,113000,9,'Nghệ An',31),(6,3,6,'https://cf.shopee.vn/file/7c8c1bc9d0e8fe1787fc11ea53b7ca44_tn','[Hình Thât] Set Yếm Chất Kaki Mềm Mịn +Kèm Áo Sơ Mi Phong Cách',300000,190000,55,'Đà Nẵng',35),(7,2,1,'https://cf.shopee.vn/file/b4b05e517339a0066a6b67d6acf00557_tn','Đầm Chữ A Không Tay Thời Trang Mùa Hè Dành Cho Nữ',276000,193000,38,'Nước ngoài',30),(8,1,1,'https://cf.shopee.vn/file/d43ed6dd1a497bc41e1e418176493d3e_tn','Set bộ cổ yếm bóng cực xinh - Freesize dưới 55kg (MẪU MỚI VỀ)',150000,129000,21,'TP. Hồ Chí Minh',18),(9,4,5,'https://cf.shopee.vn/file/277bb76071937690552a1ca70c6035ef_tn','Bộ áo dài truyền thống họa tiết hoa nhí màu trắng (cổ tròn tay lỡ)',400000,299000,34,'Hà Nội',33),(10,3,4,'https://cf.shopee.vn/file/596bac5d02f66d9d327569a1ba8578ea_tn','Quần yếm ( hàng od )',250000,189000,51,'Hà Nội',24),(11,2,3,'https://cf.shopee.vn/file/24bfd0954535c559838c6be08357daa7_tn','ĐẦM YẾM XƯỢC KHOÉT VAI',350000,250000,80,'TP. Hồ Chí Minh',28),(12,4,2,'https://cf.shopee.vn/file/27b4e64d9619004421cae6cca2d18f0d_tn','Jenny Shirt',350000,280000,30,'Hà Nội',20),(13,2,2,'https://cf.shopee.vn/file/440362077e4dfa6acfc00562e9de6673_tn','Đầm Sơ Mi Thiết Kế Đơn Giản Thanh Lịch Cho Nữ',400000,259000,60,'Nước ngoài',35),(14,1,6,'https://cf.shopee.vn/file/90539f40b6d2815260c86acaa28e8929_tn','Váy len tăm body cổ yếm',200000,100000,67,'Hà Nội',50),(15,3,3,'https://cf.shopee.vn/file/52c171248823499db94d76ae11971e90_tn','[ORDER] (PINK SAVIOR) [ĐỌC KỸ MÔ TẢ] SET MACHINE HEART ÁO SWEATER+VÁY YẾM (TÁCH SET) [HÀNG THIẾT KẾ]',600000,450000,380,'TP. Hải Phòng',25),(16,1,6,'https://cf.shopee.vn/file/0fdb050b3b8b3a4150594a1b72239490_tn','(Sẵn) Bộ lụa Satin hai dây nhỏ xinh tặng kèm mút ngực hàng QCCC',175000,170000,32,'Hà Nội',3),(17,3,6,'https://cf.shopee.vn/file/cabe9e0ed847a55d96cc22811a4fecbc_tn','BIKIBI LACY - Đồ Tắm Biển Mùa Hè 2 Mảnh Áo Cup Ngang 2 Dây Có Đệm Ngực Quần Váy Cạp Cao Có Quần Đùi',320000,160000,11,'Hà Nội',50),(18,3,1,'https://cf.shopee.vn/file/d52d51d95dca256cad906f53d3cf6e45_tn','Bộ jumpsuit Áo quây trơn phối quần ống rộng cực xinh phong cách mới',250000,189000,99,'Nghệ An',25),(19,1,6,'https://cf.shopee.vn/file/f9bb8bc634711d9360ad4905c9f192c2_tn','Sale đầm dự tiệc/prom/Đi chơi - fiona dress - váy chân bồng - v0007 chất lượng',100000,50000,123,'Hà Nội',50),(20,1,2,'https://cf.shopee.vn/file/dc56a9183ab24638e644cc3a17a387ec_tn','Đầm Polo Nữ SANMAY Váy Thun Suông Ôm Body Đẹp Thiết Kế Đi Dự Tiệc Cưới Công Sở Trẻ Trung Đi Chơi Biển Cao Cấp VD040',259000,159000,243,'Hồ Chí Minh',39),(21,1,3,'https://cf.shopee.vn/file/72d63d75fed98f4a3a1a16c631a8edbc_tn','váy nữ, đầm nữ ,chất cát hàn , trẻ trung cá tính năng động',550000,350000,38,'Hà Nội',36),(22,1,6,'https://cf.shopee.vn/file/5d6f1e9194486421e1d007dfb53ce32d_tn','Váy sườn xám tay dài cổ đứng eo cao thiết kế hở lưng thời trang dành cho nữ',193800,125500,140,'Nước ngoài',35),(23,1,6,'https://cf.shopee.vn/file/393630facec322537cdb8fbbefe719bb_tn','Set Áo Tay Ngắn Cổ Chữ V Dáng Rộng + Chân Váy Chữ A In Họa Tiết Hoa Phong Cách Hàn Quốc Thời Trang Cho Nữ',410000,205000,71,'Cần Thơ',50),(24,2,6,'https://cf.shopee.vn/file/6bab24f250304d93077a8a9b4fc55cb8_tn','ĐẦM THUN SỌC NỮ BIGSIZE QUẢNG CHÂU CAO CẤP, NGẮN TAY, CHẤT THUN',380000,190000,36,'Hà Nội',50),(25,2,5,'https://cf.shopee.vn/file/fe598f9c30eb60efdadd22e7f88a58d6_tn','SIÊU PHẨM. Đầm Nữ Polo Họa Tiết Gấu. TRUNGHIEU .Váy Body Nữ Cộc Tay Dáng Ngắn 3 Màu',200000,170000,321,'Hà Nội',27),(26,2,6,'https://cf.shopee.vn/file/35ac70d5329bf2e86f2e59838cba90b5_tn','ĐẦM MAXI CHẤM BI',520000,260000,99,'Hồ Chí Minh',50),(27,3,6,'https://cf.shopee.vn/file/cd357a6921755810137bd7750094e852_tn','BỘ ĐỒ NGỦ KHỦNG LONG XANH - KHỦNG LONG HỒNG HÌNH THÚ LIỀN THÂN CHO NAM VÀ NỮ',599000,390000,333,'Hà Nội',35),(28,4,6,'https://cf.shopee.vn/file/b5138209f385556e69348d62a95b91a1_tn','Bộ Đồ Bơi Nữ Kín Đáo Liền Thân - Bikini Dạng Váy Đi Biển Đẹp',320000,170000,93,'Hà Nội',45),(29,3,3,'https://cf.shopee.vn/file/5001189c8a4a117016aa97961fecf8b1_tn','CÓ SẴN bộ đồ bơi nữ liền thân kéo khóa trước màu đeng',200000,150000,73,'Hà Nội',25),(30,3,6,'https://cf.shopee.vn/file/7ef1463ec7b0600cd73d179e3cacd4de_tn','Bikini đồ bơi nữ dài tay liền thân có đệm mút',255000,199000,68,'Hà Nội',22),(31,3,6,'https://cf.shopee.vn/file/60b84f48b7509c95fba862e1796ac754_tn','One Bộ Đồ Bơi Liền Thân Dệt Kim Hở Lưng Co Giãn Tốt',370000,220000,49,'Hà Tĩnh',43),(32,4,6,'https://cf.shopee.vn/file/b585b0188422c018aba747b501dd6568_tn','Jumpsuit trắng',775000,689000,31,'Nước ngoài',11),(33,4,5,'https://cf.shopee.vn/file/71115175e514c5b14080293ea82ffe80_tn','Đồng phục Taste Bộ đồ lót kiểu dáng truyền thống quyến rũ cho nữ',640000,420000,39,'Hà Nội',39),(34,4,4,'https://cf.shopee.vn/file/ae7ff8ddf50aaff37ebbdca099c454b1_tn','Bộ đồ truyền thống kebaya Javanese Blouse Tunic - kebaya',700000,600000,323,'Indonesia',13),(35,3,4,'https://cf.shopee.vn/file/627daac53d686c7e79c0fa505da9e432_tn','Bộ Đồ Lót Liền Thân Chất Da Pu Gợi Cảm Dành Cho Nữ',191000,115000,188,'Nước ngoài',40),(46,3,1,'https://cf.shopee.vn/file/d21a3113d38592a833462cb23fa99fdd_tn','Đồ cute cực kì lun nè',200000,150000,0,'Nghệ An',25),(47,2,2,'https://cf.shopee.vn/file/d21a3113d38592a833462cb23fa99fdd_tn','test thôi nha',200000,150000,0,'Nha Dum',25);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-13 20:00:27

CREATE TABLE `consult_log` (
  `id` varchar(32) NOT NULL,
  `content` text DEFAULT NULL,
  `customer` varchar(50) NOT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `wechat` varchar(50) DEFAULT NULL,
  `url` varchar(256) NOT NULL,
  `type` tinyint(1) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
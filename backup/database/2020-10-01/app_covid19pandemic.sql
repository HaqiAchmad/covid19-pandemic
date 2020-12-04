-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 01 Okt 2020 pada 15.21
-- Versi server: 10.4.8-MariaDB
-- Versi PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `app_covid19pandemic`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `bck_covid19cases`
--

CREATE TABLE `bck_covid19cases` (
  `code` varchar(5) NOT NULL,
  `eng_country` varchar(35) NOT NULL,
  `idn_country` varchar(35) NOT NULL,
  `cases` int(11) NOT NULL,
  `deaths` int(11) NOT NULL,
  `recovered` int(11) NOT NULL,
  `active` int(11) NOT NULL,
  `serious` int(11) NOT NULL,
  `populasi` int(11) NOT NULL,
  `benua` enum('Asia','Afrika','Amerika Selatan','Amerika Utara','Eropa','Oceania','null') NOT NULL,
  `flag` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `bck_covid19cases`
--

INSERT INTO `bck_covid19cases` (`code`, `eng_country`, `idn_country`, `cases`, `deaths`, `recovered`, `active`, `serious`, `populasi`, `benua`, `flag`) VALUES
('WORLD', '.World.', '.Dunia.', 32138014, 982722, 23708311, 7446981, 62385, 781423273, 'null', ''),
('', 'Afghanistan', '', 39170, 1451, 32619, 5100, 93, 39124705, 'Asia', ''),
('', 'Albania', '', 12787, 370, 7139, 5278, 18, 2877060, 'Eropa', ''),
('', 'Algeria', '', 50400, 1698, 35428, 13274, 31, 44030062, 'Afrika', ''),
('', 'Andorra', '', 1753, 53, 1203, 497, 4, 77294, 'Eropa', ''),
('', 'Angola', '', 4475, 162, 1503, 2810, 8, 33093817, 'Afrika', ''),
('', 'Anguilla', '', 3, 0, 3, 0, 0, 15034, 'Amerika Utara', ''),
('', 'Antigua and Barbuda', '', 97, 3, 92, 2, 0, 98116, 'Amerika Utara', ''),
('', 'Argentina', '', 664799, 14376, 525486, 124937, 3511, 45291222, 'Amerika Selatan', ''),
('', 'Armenia', '', 48251, 945, 43266, 4040, 0, 2964535, 'Asia', ''),
('', 'Aruba', '', 3721, 25, 2501, 1195, 11, 106871, 'Amerika Utara', ''),
('', 'Australia', '', 26980, 861, 24448, 1671, 10, 25567653, 'Oceania', ''),
('', 'Austria', '', 40816, 783, 31661, 8372, 78, 9018304, 'Eropa', ''),
('', 'Azerbaijan', '', 39686, 581, 37255, 1850, 0, 10160466, 'Asia', ''),
('', 'Bahamas', '', 3618, 80, 1915, 1623, 79, 394108, 'Amerika Utara', ''),
('', 'Bahrain', '', 67701, 231, 60853, 6617, 59, 1714632, 'Asia', ''),
('', 'Bangladesh', '', 355384, 5072, 265092, 85220, 0, 165070897, 'Asia', ''),
('', 'Barbados', '', 189, 7, 174, 8, 0, 287457, 'Amerika Utara', ''),
('', 'Belarus', '', 76651, 802, 73733, 2116, 0, 9448595, 'Eropa', ''),
('', 'Belgium', '', 106887, 9959, 19079, 77849, 95, 11601339, 'Eropa', ''),
('', 'Belize', '', 1706, 22, 1019, 665, 3, 399260, 'Amerika Utara', ''),
('', 'Benin', '', 2325, 40, 1954, 331, 0, 12194632, 'Afrika', ''),
('', 'Bermuda', '', 181, 9, 48, 20, 0, 62224, 'Amerika Utara', ''),
('', 'Bhutan', '', 261, 0, 196, 65, 0, 773580, 'Asia', ''),
('', 'Bolivia', '', 131990, 7731, 91556, 32703, 71, 11709345, 'Amerika Selatan', ''),
('', 'Bosnia and Herzegovina', '', 26081, 790, 18634, 6657, 0, 3275988, 'Eropa', ''),
('', 'Botswana', '', 2921, 16, 701, 2204, 1, 2362444, 'Afrika', ''),
('', 'Brazil', '', 4627780, 139065, 3992886, 495829, 8318, 212908548, 'Amerika Selatan', ''),
('', 'British Virgin Islands', '', 69, 1, 48, 20, 2, 30278, 'Amerika Utara', ''),
('', 'Brunei', '', 146, 3, 142, 1, 0, 438454, 'Asia', ''),
('', 'Bulgaria', '', 19283, 779, 13867, 4637, 29, 6936034, 'Eropa', ''),
('', 'Burkina Faso', '', 1950, 56, 1260, 634, 0, 21031875, 'Afrika', ''),
('', 'Burundi', '', 477, 1, 462, 14, 0, 11969834, 'Afrika', ''),
('', 'Cabo Verde', '', 5479, 55, 4917, 507, 0, 557389, 'Afrika', ''),
('', 'Cambodia', '', 275, 0, 274, 1, 0, 16772360, 'Asia', ''),
('', 'Cameroon', '', 20712, 418, 19440, 854, 30, 26694924, 'Afrika', ''),
('', 'Canada', '', 147756, 9243, 127788, 10725, 86, 37818373, 'Amerika Utara', ''),
('', 'CAR', '', 4804, 62, 1837, 2905, 2, 4849008, 'Afrika', ''),
('', 'Caribbean Netherlands', '', 69, 1, 21, 47, 0, 26279, 'Amerika Utara', ''),
('', 'Cayman Islands', '', 210, 1, 205, 4, 0, 65899, 'Amerika Utara', ''),
('', 'Chad', '', 1171, 82, 1003, 86, 0, 16531324, 'Afrika', ''),
('', 'Channel Islands', '', 654, 48, 575, 31, 2, 174232, 'Eropa', ''),
('', 'Chile', '', 449903, 12345, 425165, 12393, 907, 19154009, 'Amerika Selatan', ''),
('', 'China', '', 85322, 4634, 80522, 166, 4, 1439323776, 'Asia', ''),
('', 'Colombia', '', 784268, 24746, 662277, 97245, 863, 51007347, 'Amerika Selatan', ''),
('', 'Comoros', '', 474, 7, 453, 14, 0, 873811, 'Afrika', ''),
('', 'Congo', '', 5005, 89, 3887, 1029, 0, 5548744, 'Afrika', ''),
('', 'Costa Rica', '', 68059, 781, 26136, 41142, 249, 5104825, 'Amerika Utara', ''),
('', 'Croatia', '', 15572, 261, 14111, 1200, 24, 4099274, 'Eropa', ''),
('', 'Cuba', '', 5270, 118, 4582, 570, 8, 11324995, 'Amerika Utara', ''),
('', 'Curaçao', '', 301, 1, 104, 196, 1, 164249, 'Amerika Utara', ''),
('', 'Cyprus', '', 1663, 22, 1369, 272, 1, 1209413, 'Asia', ''),
('', 'Czechia', '', 55464, 555, 26709, 28200, 120, 10713617, 'Eropa', ''),
('', 'Denmark', '', 24916, 645, 18646, 5625, 14, 5796947, 'Eropa', ''),
('', 'Djibouti', '', 5407, 61, 5339, 7, 0, 991311, 'Afrika', ''),
('', 'Dominica', '', 24, 0, 18, 6, 0, 72028, 'Amerika Utara', ''),
('', 'Dominican Republic', '', 109737, 2074, 83434, 24229, 208, 10872904, 'Amerika Utara', ''),
('', 'DRC', '', 10555, 271, 10051, 233, 0, 90168360, 'Afrika', ''),
('', 'Ecuador', '', 129892, 11171, 102852, 15869, 360, 17703980, 'Amerika Selatan', ''),
('', 'Egypt', '', 102513, 5835, 92644, 4034, 41, 102775319, 'Afrika', ''),
('', 'El Salvador', '', 28201, 823, 22326, 5052, 89, 6493797, 'Amerika Utara', ''),
('', 'Equatorial Guinea', '', 5018, 83, 4530, 405, 4, 1413204, 'Afrika', ''),
('', 'Eritrea', '', 369, 0, 330, 39, 0, 3557748, 'Afrika', ''),
('', 'Estonia', '', 3076, 64, 2395, 617, 2, 1326744, 'Eropa', ''),
('', 'Eswatini', '', 5375, 108, 4724, 543, 11, 1162955, 'Afrika', ''),
('', 'Ethiopia', '', 71687, 1148, 29461, 41078, 267, 115606131, 'Afrika', ''),
('', 'Faeroe Islands', '', 451, 0, 416, 35, 0, 48906, 'Eropa', ''),
('', 'Falkland Islands', '', 13, 0, 13, 0, 0, 3502, 'Amerika Selatan', ''),
('', 'Fiji', '', 32, 2, 28, 2, 1, 897946, 'Oceania', ''),
('', 'Finland', '', 9379, 343, 7850, 1186, 4, 5542730, 'Eropa', ''),
('', 'France', '', 481141, 31459, 93538, 356144, 951, 65307193, 'Eropa', ''),
('', 'French Guiana', '', 9762, 65, 9431, 266, 6, 300403, 'Amerika Selatan', ''),
('', 'French Polynesia', '', 1469, 5, 1237, 227, 5, 281284, 'Oceania', ''),
('', 'Gabon', '', 8716, 54, 7906, 756, 2, 2237614, 'Afrika', ''),
('', 'Gambia', '', 3552, 110, 2012, 1430, 0, 2431879, 'Afrika', ''),
('', 'Georgia', '', 4399, 26, 1705, 2668, 0, 3987347, 'Asia', ''),
('', 'Germany', '', 279205, 9508, 249500, 20197, 293, 83846303, 'Eropa', ''),
('', 'Ghana', '', 46222, 299, 45417, 506, 6, 31220495, 'Afrika', ''),
('', 'Gibraltar', '', 361, 0, 331, 30, 0, 33689, 'Eropa', ''),
('', 'Greece', '', 16286, 357, 9989, 5940, 73, 10411029, 'Eropa', ''),
('', 'Greenland', '', 14, 0, 14, 0, 0, 56793, 'Amerika Utara', ''),
('', 'Grenada', '', 24, 0, 24, 0, 0, 56793, 'Amerika Utara', ''),
('', 'Guadeloupe', '', 4487, 42, 2199, 2246, 24, 400140, 'Amerika Utara', ''),
('', 'Guatemala', '', 87442, 3154, 76459, 7829, 5, 17990424, 'Amerika Utara', ''),
('', 'Guinea', '', 10434, 65, 9801, 568, 24, 13212769, 'Afrika', ''),
('', 'Guinea-Bissau', '', 2324, 39, 1549, 736, 5, 1978522, 'Afrika', ''),
('', 'Guyana', '', 2535, 69, 1464, 1002, 14, 787433, 'Amerika Selatan', ''),
('', 'Haiti', '', 8646, 225, 6551, 1870, 0, 11434330, 'Amerika Utara', ''),
('', 'Honduras', '', 72675, 2222, 24022, 46431, 20, 9940379, 'Amerika Utara', ''),
('', 'Hong Kong', '', 5057, 104, 4758, 195, 13, 7511174, 'Asia', ''),
('', 'Hungary', '', 21200, 709, 4818, 15673, 32, 9654581, 'Eropa', ''),
('', 'Iceland', '', 2512, 10, 2150, 352, 0, 341755, 'Eropa', ''),
('', 'India', '', 5816103, 92317, 4752991, 970795, 8944, 1383159972, 'Asia', ''),
('', 'Indonesia', '', 262022, 10105, 191853, 60064, 0, 274195355, 'Asia', ''),
('', 'Iran', '', 436319, 25015, 367829, 43475, 3957, 84241549, 'Asia', ''),
('', 'Iraq', '', 337106, 8799, 268761, 59546, 527, 40427156, 'Asia', ''),
('', 'Ireland', '', 33675, 1794, 23364, 8517, 16, 4950431, 'Eropa', ''),
('', 'Isle of Man', '', 340, 24, 312, 4, 0, 85137, 'Eropa', ''),
('', 'Israel', '', 214458, 1378, 152294, 60786, 669, 9197590, 'Asia', ''),
('', 'Italy', '', 302537, 35758, 220665, 46114, 244, 60440954, 'Eropa', ''),
('', 'Ivory Coast', '', 19501, 120, 19003, 378, 0, 26525664, 'Afrika', ''),
('', 'Jamaica', '', 5395, 76, 1444, 3875, 7, 2964169, 'Amerika Utara', ''),
('', 'Japan', '', 80041, 1520, 72538, 5983, 166, 126384252, 'Asia', ''),
('', 'Jordan', '', 6591, 36, 3937, 2618, 13, 10226688, 'Asia', ''),
('', 'Kazakhstan', '', 107590, 1699, 102360, 3531, 221, 18828728, 'Asia', ''),
('', 'Kenya', '', 37489, 669, 24334, 12486, 44, 54040099, 'Afrika', ''),
('', 'Kuwait', '', 101851, 592, 92961, 8298, 101, 4285118, 'Asia', ''),
('', 'Kyrgyzstan', '', 45932, 1063, 42147, 2722, 24, 6548902, 'Asia', ''),
('', 'Laos', '', 23, 0, 22, 1, 0, 651378, 'Asia', ''),
('', 'Latvia', '', 1594, 36, 1248, 310, 0, 1881220, 'Eropa', ''),
('', 'Lebanon', '', 32819, 329, 14112, 18378, 139, 6818148, 'Asia', ''),
('', 'Lesotho', '', 1558, 35, 797, 726, 0, 2146213, 'Afrika', ''),
('', 'Liberia', '', 1338, 82, 1221, 35, 0, 5084576, 'Afrika', ''),
('', 'Libya', '', 30632, 474, 16842, 13316, 0, 6892864, 'Afrika', ''),
('', 'Liechtenstein', '', 116, 1, 110, 5, 0, 38153, 'Eropa', ''),
('', 'Lithuania', '', 4070, 89, 2253, 1728, 0, 2713177, 'Eropa', ''),
('', 'Luxembourg', '', 8090, 124, 6862, 1104, 1, 628289, 'Eropa', ''),
('', 'Macao', '', 46, 0, 46, 0, 0, 651378, 'Asia', ''),
('', 'Madagascar', '', 16221, 228, 14867, 1126, 11, 27851325, 'Afrika', ''),
('', 'Malawi', '', 5747, 179, 4163, 1405, 4, 19241237, 'Afrika', ''),
('', 'Malaysia', '', 10576, 133, 9666, 777, 6, 32461889, 'Asia', ''),
('', 'Maldives', '', 9939, 34, 8597, 1308, 12, 542724, 'Asia', ''),
('', 'Mali', '', 3041, 130, 2391, 520, 0, 20381309, 'Afrika', ''),
('', 'Malta', '', 2898, 27, 2191, 680, 0, 441817, 'Eropa', ''),
('', 'Martinique', '', 1290, 20, 98, 1172, 9, 375197, 'Amerika Utara', ''),
('', 'Mauritania', '', 7433, 161, 7052, 220, 3, 4677146, 'Afrika', ''),
('', 'Mauritius', '', 367, 10, 343, 14, 0, 1272266, 'Afrika', ''),
('', 'Mayotte', '', 3541, 40, 2964, 537, 2, 274302, 'Afrika', ''),
('', 'Mexico', '', 710049, 74949, 510237, 124863, 2677, 129243704, 'Amerika Utara', ''),
('', 'Moldova', '', 48232, 1244, 36071, 10917, 564, 4031759, 'Eropa', ''),
('', 'Monaco', '', 199, 1, 165, 33, 1, 39306, 'Eropa', ''),
('', 'Mongolia', '', 313, 0, 303, 10, 1, 3290418, 'Asia', ''),
('', 'Montenegro', '', 9428, 151, 5728, 3549, 0, 628085, 'Eropa', ''),
('', 'Montserrat', '', 13, 1, 12, 0, 0, 4993, 'Amerika Utara', ''),
('', 'Morocco', '', 110099, 1956, 90186, 17957, 289, 37011915, 'Afrika', ''),
('', 'Mozambique', '', 7399, 51, 4558, 2790, 0, 31451650, 'Afrika', ''),
('', 'Myanmar', '', 8515, 155, 2381, 5979, 0, 54495142, 'Asia', ''),
('', 'Namibia', '', 10740, 119, 8482, 2139, 15, 2551433, 'Afrika', ''),
('', 'Nepal', '', 69301, 453, 50411, 18437, 0, 29256737, 'Asia', ''),
('', 'Netherlands', '', 100597, 6296, -1, -1, 104, 17143713, 'Eropa', ''),
('', 'New Caledonia', '', 26, 0, 26, 0, 0, 286129, 'Oceania', ''),
('', 'New Zealand', '', 1827, 25, 1737, 65, 3, 5002100, 'Oceania', ''),
('', 'Nicaragua', '', 5073, 149, 2913, 2011, 0, 6642596, 'Amerika Utara', ''),
('', 'Niger', '', 1194, 69, 1107, 18, 0, 24399460, 'Afrika', ''),
('', 'Nigeria', '', 57849, 1102, 49098, 7649, 7, 207292385, 'Afrika', ''),
('', 'North Macedonia', '', 17049, 710, 14186, 2153, 3, 2083354, 'Eropa', ''),
('', 'Norway', '', 13277, 267, 10371, 2639, 2, 5431023, 'Eropa', ''),
('', 'Oman', '', 95907, 885, 86765, 8257, 185, 5135890, 'Asia', ''),
('', 'Pakistan', '', 309015, 6444, 294740, 7831, 544, 221871028, 'Asia', ''),
('', 'Palestine', '', 37591, 274, 26934, 10383, 0, 5128259, 'Asia', ''),
('', 'Panama', '', 107990, 2291, 84437, 21262, 122, 4330196, 'Amerika Utara', ''),
('', 'Papua New Guinea', '', 527, 7, 232, 288, 0, 8985270, 'Oceania', ''),
('', 'Paraguay', '', 35571, 727, 19867, 14977, 157, 7152579, 'Amerika Selatan', ''),
('', 'Peru', '', 782695, 31870, 636489, 114336, 1381, 33076571, 'Amerika Selatan', ''),
('', 'Philippines', '', 296755, 5127, 231928, 59700, 1758, 109918002, 'Asia', ''),
('', 'Poland', '', 82809, 2369, 66158, 14282, 91, 37836886, 'Eropa', ''),
('', 'Portugal', '', 70465, 1928, 46290, 18586, 506, 19207285, 'Eropa', ''),
('', 'Qatar', '', 124425, 212, 121263, 2950, 62, 2807805, 'Asia', ''),
('', 'Réunion', '', 3501, 11, 2482, 1008, 0, 896806, 'Afrika', ''),
('', 'Romania', '', 118054, 4591, 94877, 18586, 506, 19207285, 'Eropa', ''),
('', 'Russia', '', 1128836, 19948, 929829, 179059, 2300, 145949102, 'Eropa', ''),
('', 'Rwanda', '', 4789, 27, 3050, 1712, 0, 13024661, 'Afrika', ''),
('', 'S. Korea', '', 23455, 395, 20978, 2082, 128, 51279620, 'Asia', ''),
('', 'Saint Kitts and Nevis', '', 19, 0, 17, 2, 0, 53286, 'Amerika Utara', ''),
('', 'Saint Lucia', '', 27, 0, 26, 1, 0, 183822, 'Amerika Utara', ''),
('', 'Saint Martin', '', 367, 8, 273, 86, 11, 38815, 'Amerika Utara', ''),
('', 'Saint Pierre Miquelon', '', 16, 0, 6, 10, 0, 5787, 'Amerika Utara', ''),
('', 'San Marino', '', 723, 42, 669, 12, 3, 33948, 'Eropa', ''),
('', 'Sao Tome and Principe', '', 910, 15, 881, 14, 0, 220089, 'Afrika', ''),
('', 'Saudi Arabia', '', 331857, 4599, 314793, 12465, 1090, 34938554, 'Asia', ''),
('', 'Senegal', '', 14816, 304, 11818, 2694, 24, 16843152, 'Afrika', ''),
('', 'Serbia', '', 33080, 744, 31536, 800, 22, 8729071, 'Eropa', ''),
('', 'Seychelles', '', 143, 0, 140, 3, 0, 98490, 'Afrika', ''),
('', 'Sierra Leone', '', 2188, 72, 1666, 450, 0, 8013928, 'Afrika', ''),
('', 'Singapore', '', 57654, 27, 57333, 294, 0, 5861084, 'Asia', ''),
('', 'Sint Maarten', '', 616, 21, 517, 78, 6, 42988, 'Amerika Utara', ''),
('', 'Slovakia', '', 7629, 41, 3978, 3610, 25, 5460261, 'Eropa', ''),
('', 'Slovenia', '', 4694, 143, 3168, 1383, 20, 2079005, 'Eropa', ''),
('', 'Somalia', '', 3465, 98, 2877, 490, 0, 15992604, 'Afrika', ''),
('', 'South Africa', '', 667049, 16283, 595916, 54850, 539, 59481669, 'Afrika', ''),
('', 'South Sudan', '', 2669, 49, 1290, 1330, 0, 11224136, 'Afrika', ''),
('', 'Spain', '', 693556, 31034, -1, -1, 1436, 46759015, 'Eropa', ''),
('', 'Sri Lanka', '', 3333, 13, 3142, 178, 0, 21434354, 'Asia', ''),
('', 'St. Barth', '', 45, 0, 25, 20, 0, 9884, 'Amerika Utara', ''),
('', 'St. Vincent Grenadines', '', 64, 0, 64, 0, 0, 111022, 'Amerika Utara', ''),
('', 'Sudan', '', 13592, 836, 6764, 5992, 0, 44080967, 'Afrika', ''),
('', 'Suriname', '', 4779, 101, 4560, 118, 5, 587842, 'Amerika Selatan', ''),
('', 'Sweden', '', 89756, 5876, -1, -1, 15, 10113840, 'Eropa', ''),
('', 'Switzerland', '', 51492, 2061, 42300, 7131, 34, 8669241, 'Eropa', ''),
('', 'Syria', '', 3966, 183, 1013, 2770, 0, 17596682, 'Asia', ''),
('', 'Taiwan', '', 509, 7, 480, 22, 0, 23826952, 'Asia', ''),
('', 'Tajikistan', '', 9520, 74, 8296, 1150, 0, 9586218, 'Asia', ''),
('', 'Tanzania', '', 509, 21, 183, 305, 7, 60115081, 'Afrika', ''),
('', 'Thailand', '', 3519, 59, 3360, 100, 1, 69841277, 'Asia', ''),
('', 'Timor-Leste', '', 27, 0, 27, 0, 0, 1324179, 'Asia', ''),
('', 'Togo', '', 1707, 180, 5032, 8093, 74, 11847346, 'Afrika', ''),
('', 'Trinidad and Tobago', '', 4136, 67, 1960, 2109, 11, 1400543, 'Amerika Utara', ''),
('', 'Tunisia', '', 13305, 180, 5032, 8093, 74, 11847346, 'Afrika', ''),
('', 'Turkey', '', 309790, 7785, 271964, 30041, 1573, 84549763, 'Asia', ''),
('', 'Turks and Caicos', '', 676, 5, 588, 83, 3, 38837, 'Amerika Utara', ''),
('', 'UAE', '', 88532, 407, 77937, 10188, 0, 9918074, 'Asia', ''),
('', 'Uganda', '', 7218, 71, 3611, 3536, 0, 46062181, 'Afrika', ''),
('', 'UK', '', 409729, 41862, -1, -1, 211, 67968701, 'Eropa', ''),
('', 'Ukraine', '', 188106, 3757, 83458, 100891, 177, 43671581, 'Eropa', ''),
('', 'Uruguay', '', 1946, 47, 1661, 238, 2, 3476531, 'Amerika Selatan', ''),
('', 'USA', '', 7140137, 206598, 4399996, 2533543, 14086, 331452210, 'Amerika Utara', ''),
('', 'Uzbekistan', '', 53966, 447, 50441, 3078, 283, 33580993, 'Asia', ''),
('', 'Vatican City', '', 12, 0, 12, 0, 0, 801, 'Eropa', ''),
('', 'Venezuela', '', 69439, 574, 58759, 10106, 146, 28416979, 'Amerika Selatan', ''),
('', 'Vietnam', '', 1069, 35, 991, 43, 0, 97542604, 'Asia', ''),
('', 'Western Sahara', '', 10, 1, 8, 1, 0, 600654, 'Afrika', ''),
('', 'Yemen', '', 2029, 586, 1250, 193, 0, 29975045, 'Asia', ''),
('', 'Zambia', '', 14491, 332, 13643, 516, 0, 18499324, 'Afrika', ''),
('', 'Zimbabwe', '', 7752, 227, 6043, 1482, 0, 14912782, 'Afrika', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `covid19cases`
--

CREATE TABLE `covid19cases` (
  `code` varchar(5) NOT NULL,
  `eng_country` varchar(35) NOT NULL,
  `idn_country` varchar(35) NOT NULL,
  `cases` int(11) NOT NULL,
  `deaths` int(11) NOT NULL,
  `recovered` int(11) NOT NULL,
  `active` int(11) NOT NULL,
  `serious` int(11) NOT NULL,
  `populasi` int(11) NOT NULL,
  `benua` enum('Asia','Afrika','Amerika Selatan','Amerika Utara','Eropa','Oceania','null') NOT NULL,
  `flag` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `covid19cases`
--

INSERT INTO `covid19cases` (`code`, `eng_country`, `idn_country`, `cases`, `deaths`, `recovered`, `active`, `serious`, `populasi`, `benua`, `flag`) VALUES
('ABW', 'Aruba', 'Aruba', 3721, 25, 2501, 1195, 11, 106871, 'Amerika Utara', 'default'),
('AFG', 'Afghanistan', 'Afganistan', 39170, 1451, 32619, 5100, 93, 39124705, 'Asia', 'default'),
('AGO', 'Angola', 'Angola', 4475, 162, 1503, 2810, 8, 33093817, 'Afrika', 'default'),
('AIA', 'Anguilla', 'Anguilla', 3, 0, 3, 0, 0, 15034, 'Amerika Utara', 'default'),
('ALB', 'Albania', 'Albania', 12787, 370, 7139, 5278, 18, 2877060, 'Eropa', 'default'),
('AND', 'Andorra', 'Andorra', 1753, 53, 1203, 497, 4, 77294, 'Eropa', 'default'),
('ARE', 'UAE', 'Uni Emirat Arab', 88532, 407, 77937, 10188, 0, 9918074, 'Asia', 'default'),
('ARG', 'Argentina', 'Argentina', 664799, 14376, 525486, 124937, 3511, 45291222, 'Amerika Selatan', 'default'),
('ARM', 'Armenia', 'Armenia', 48251, 945, 43266, 4040, 0, 2964535, 'Asia', 'default'),
('ATG', 'Antigua and Barbuda', 'Antigua dan Barbuda', 97, 3, 92, 2, 0, 98116, 'Amerika Utara', 'default'),
('AUS', 'Australia', 'Australia', 26980, 861, 24448, 1671, 10, 25567653, 'Oceania', 'default'),
('AUT', 'Austria', 'Austria', 40816, 783, 31661, 8372, 78, 9018304, 'Eropa', 'default'),
('AZE', 'Azerbaijan', 'Azerbaijan', 39686, 581, 37255, 1850, 0, 10160466, 'Asia', 'default'),
('BDI', 'Burundi', 'Burundi', 477, 1, 462, 14, 0, 11969834, 'Afrika', 'default'),
('BEL', 'Belgium', 'Belgia', 106887, 9959, 19079, 77849, 95, 11601339, 'Eropa', 'default'),
('BEN', 'Benin', 'Benin', 2325, 40, 1954, 331, 0, 12194632, 'Afrika', 'default'),
('BFA', 'Burkina Faso', 'Burkina Faso', 1950, 56, 1260, 634, 0, 21031875, 'Afrika', 'default'),
('BGD', 'Bangladesh', 'Bangladesh', 355384, 5072, 265092, 85220, 0, 165070897, 'Asia', 'default'),
('BGR', 'Bulgaria', 'Bulgaria', 19283, 779, 13867, 4637, 29, 6936034, 'Eropa', 'default'),
('BHR', 'Bahrain', 'Bahrain', 67701, 231, 60853, 6617, 59, 1714632, 'Asia', 'default'),
('BHS', 'Bahamas', 'Bahama', 3618, 80, 1915, 1623, 79, 394108, 'Amerika Utara', 'default'),
('BIH', 'Bosnia and Herzegovina', 'Bosnia dan Herzegovina', 26081, 790, 18634, 6657, 0, 3275988, 'Eropa', 'default'),
('BLM', 'St. Barth', 'Saint Barthélemy', 45, 0, 25, 20, 0, 9884, 'Amerika Utara', 'default'),
('BLR', 'Belarus', 'Belarus', 76651, 802, 73733, 2116, 0, 9448595, 'Eropa', 'default'),
('BLZ', 'Belize', 'Belize', 1706, 22, 1019, 665, 3, 399260, 'Amerika Utara', 'default'),
('BMU', 'Bermuda', 'Bermuda', 181, 9, 48, 20, 0, 62224, 'Amerika Utara', 'default'),
('BOL', 'Bolivia', 'Bolivia', 131990, 7731, 91556, 32703, 71, 11709345, 'Amerika Selatan', 'default'),
('BRA', 'Brazil', 'Brasil', 4627780, 139065, 3992886, 495829, 8318, 212908548, 'Amerika Selatan', 'default'),
('BRB', 'Barbados', 'Barbados', 189, 7, 174, 8, 0, 287457, 'Amerika Utara', 'default'),
('BRN', 'Brunei', 'Brunei Darussalam', 146, 3, 142, 1, 0, 438454, 'Asia', 'default'),
('BTN', 'Bhutan', 'Bhutan', 261, 0, 196, 65, 0, 773580, 'Asia', 'default'),
('BWA', 'Botswana', 'Bostwana', 2921, 16, 701, 2204, 1, 2362444, 'Afrika', 'default'),
('CAF', 'CAR', 'Republik Afrika Tengah', 4804, 62, 1837, 2905, 2, 4849008, 'Afrika', 'default'),
('CAN', 'Canada', 'Kanada', 147756, 9243, 127788, 10725, 86, 37818373, 'Amerika Utara', 'default'),
('CHE', 'Switzerland', 'Swiss', 51492, 2061, 42300, 7131, 34, 8669241, 'Eropa', 'default'),
('CHL', 'Chile', 'Chili', 449903, 12345, 425165, 12393, 907, 19154009, 'Amerika Selatan', 'default'),
('CHN', 'China', 'Cina', 85322, 4634, 80522, 166, 4, 1439323776, 'Asia', 'default'),
('CIV', 'Ivory Coast', 'Pantai Gading', 19501, 120, 19003, 378, 0, 26525664, 'Afrika', 'default'),
('CMR', 'Cameroon', 'Kamerun', 20712, 418, 19440, 854, 30, 26694924, 'Afrika', 'default'),
('COD', 'DRC', 'Republik Demokratik Kongo', 10555, 271, 10051, 233, 0, 90168360, 'Afrika', 'default'),
('COG', 'Congo', 'Kongo', 5005, 89, 3887, 1029, 0, 5548744, 'Afrika', 'default'),
('COL', 'Colombia', 'Kolombia', 784268, 24746, 662277, 97245, 863, 51007347, 'Amerika Selatan', 'default'),
('COM', 'Comoros', 'Komoro', 474, 7, 453, 14, 0, 873811, 'Afrika', 'default'),
('CPV', 'Cabo Verde', 'Tanjung Verde', 5479, 55, 4917, 507, 0, 557389, 'Afrika', 'default'),
('CRI', 'Costa Rica', 'Kosta Rika', 68059, 781, 26136, 41142, 249, 5104825, 'Amerika Utara', 'default'),
('CUB', 'Cuba', 'Kuba', 5270, 118, 4582, 570, 8, 11324995, 'Amerika Utara', 'default'),
('CUW', 'Curaçao', 'Curaçao', 301, 1, 104, 196, 1, 164249, 'Amerika Utara', 'default'),
('CYM', 'Cayman Islands', 'Cayman Kepulauan', 210, 1, 205, 4, 0, 65899, 'Amerika Utara', 'default'),
('CYP', 'Cyprus', 'Siprus', 1663, 22, 1369, 272, 1, 1209413, 'Asia', 'default'),
('CZE', 'Czechia', 'Republik Ceko', 55464, 555, 26709, 28200, 120, 10713617, 'Eropa', 'default'),
('DEU', 'Germany', 'Jerman', 279205, 9508, 249500, 20197, 293, 83846303, 'Eropa', 'default'),
('DJI', 'Djibouti', 'Djibouti', 5407, 61, 5339, 7, 0, 991311, 'Afrika', 'default'),
('DMA', 'Dominica', 'Dominika', 24, 0, 18, 6, 0, 72028, 'Amerika Utara', 'default'),
('DNK', 'Denmark', 'Denmark', 24916, 645, 18646, 5625, 14, 5796947, 'Eropa', 'default'),
('DOM', 'Dominican Republic', 'Republik Dominika', 109737, 2074, 83434, 24229, 208, 10872904, 'Amerika Utara', 'default'),
('DZA', 'Algeria', 'Aljazair', 50400, 1698, 35428, 13274, 31, 44030062, 'Afrika', 'default'),
('ECU', 'Ecuador', 'Ekuador', 129892, 11171, 102852, 15869, 360, 17703980, 'Amerika Selatan', 'default'),
('EGY', 'Egypt', 'Mesir', 102513, 5835, 92644, 4034, 41, 102775319, 'Afrika', 'default'),
('ERI', 'Eritrea', 'Eritrea', 369, 0, 330, 39, 0, 3557748, 'Afrika', 'default'),
('ESH', 'Western Sahara', 'Sahara Barat', 10, 1, 8, 1, 0, 600654, 'Afrika', 'default'),
('ESP', 'Spain', 'Spanyol', 693556, 31034, -1, -1, 1436, 46759015, 'Eropa', 'default'),
('EST', 'Estonia', 'Estonia', 3076, 64, 2395, 617, 2, 1326744, 'Eropa', 'default'),
('ETH', 'Ethiopia', 'Ethiopia', 71687, 1148, 29461, 41078, 267, 115606131, 'Afrika', 'default'),
('FIN', 'Finland', 'Finlandia', 9379, 343, 7850, 1186, 4, 5542730, 'Eropa', 'default'),
('FJI', 'Fiji', 'Fiji', 32, 2, 28, 2, 1, 897946, 'Oceania', 'default'),
('FLK', 'Falkland Islands', 'Kepulauan Falkland', 13, 0, 13, 0, 0, 3502, 'Amerika Selatan', 'default'),
('FRA', 'France', 'Prancis', 481141, 31459, 93538, 356144, 951, 65307193, 'Eropa', 'default'),
('FRO', 'Faeroe Islands', 'Kepulauan Foroe', 451, 0, 416, 35, 0, 48906, 'Eropa', 'default'),
('GAB', 'Gabon', 'Gabon', 8716, 54, 7906, 756, 2, 2237614, 'Afrika', 'default'),
('GBR', 'UK', 'Britania Raya', 409729, 41862, -1, -1, 211, 67968701, 'Eropa', 'default'),
('GEO', 'Georgia', 'Georgia', 4399, 26, 1705, 2668, 0, 3987347, 'Asia', 'default'),
('GHA', 'Ghana', 'Ghana', 46222, 299, 45417, 506, 6, 31220495, 'Afrika', 'default'),
('GIB', 'Gibraltar', 'Gibraltar', 361, 0, 331, 30, 0, 33689, 'Eropa', 'default'),
('GIN', 'Guinea', 'Guinea', 10434, 65, 9801, 568, 24, 13212769, 'Afrika', 'default'),
('GLP', 'Guadeloupe', 'Guadeloupe', 4487, 42, 2199, 2246, 24, 400140, 'Amerika Utara', 'default'),
('GMB', 'Gambia', 'Gambia', 3552, 110, 2012, 1430, 0, 2431879, 'Afrika', 'default'),
('GNB', 'Guinea-Bissau', 'Guinea-Bissau', 2324, 39, 1549, 736, 5, 1978522, 'Afrika', 'default'),
('GNQ', 'Equatorial Guinea', 'Guinea Khatulistiwa', 5018, 83, 4530, 405, 4, 1413204, 'Afrika', 'default'),
('GRC', 'Greece', 'Yunani', 16286, 357, 9989, 5940, 73, 10411029, 'Eropa', 'default'),
('GRD', 'Grenada', 'Grenada', 24, 0, 24, 0, 0, 56793, 'Amerika Utara', 'default'),
('GRL', 'Greenland', 'Greenland', 14, 0, 14, 0, 0, 56793, 'Amerika Utara', 'default'),
('GTM', 'Guatemala', 'Guatemala', 87442, 3154, 76459, 7829, 5, 17990424, 'Amerika Utara', 'default'),
('GUF', 'French Guiana', 'Guyana Prancis', 9762, 65, 9431, 266, 6, 300403, 'Amerika Selatan', 'default'),
('GUY', 'Guyana', 'Guyana', 2535, 69, 1464, 1002, 14, 787433, 'Amerika Selatan', 'default'),
('HKG', 'Hong Kong', 'Hong Kong', 5057, 104, 4758, 195, 13, 7511174, 'Asia', 'default'),
('HND', 'Honduras', 'Honduras', 72675, 2222, 24022, 46431, 20, 9940379, 'Amerika Utara', 'default'),
('HRV', 'Croatia', 'Kroasia', 15572, 261, 14111, 1200, 24, 4099274, 'Eropa', 'default'),
('HTI', 'Haiti', 'Haiti', 8646, 225, 6551, 1870, 0, 11434330, 'Amerika Utara', 'default'),
('HUN', 'Hungary', 'Hungaria', 21200, 709, 4818, 15673, 32, 9654581, 'Eropa', 'default'),
('IDN', 'Indonesia', 'Indonesia', 262022, 10105, 191853, 60064, 0, 274195355, 'Asia', 'default'),
('IMN', 'Isle of Man', 'Pulau Man', 340, 24, 312, 4, 0, 85137, 'Eropa', 'default'),
('IND', 'India', 'India', 5816103, 92317, 4752991, 970795, 8944, 1383159972, 'Asia', 'default'),
('IRL', 'Ireland', 'Irlandia', 33675, 1794, 23364, 8517, 16, 4950431, 'Eropa', 'default'),
('IRN', 'Iran', 'Iran', 436319, 25015, 367829, 43475, 3957, 84241549, 'Asia', 'default'),
('IRQ', 'Iraq', 'Iraq', 337106, 8799, 268761, 59546, 527, 40427156, 'Asia', 'default'),
('ISL', 'Iceland', 'Islandia', 2512, 10, 2150, 352, 0, 341755, 'Eropa', 'default'),
('ISR', 'Israel', 'Israel', 214458, 1378, 152294, 60786, 669, 9197590, 'Asia', 'default'),
('ITA', 'Italy', 'Italia', 302537, 35758, 220665, 46114, 244, 60440954, 'Eropa', 'default'),
('JAM', 'Jamaica', 'Jamaika', 5395, 76, 1444, 3875, 7, 2964169, 'Amerika Utara', 'default'),
('JOR', 'Jordan', 'Yordania', 6591, 36, 3937, 2618, 13, 10226688, 'Asia', 'default'),
('JPN', 'Japan', 'Jepang', 80041, 1520, 72538, 5983, 166, 126384252, 'Asia', 'default'),
('KAZ', 'Kazakhstan', 'Kazakhstan', 107590, 1699, 102360, 3531, 221, 18828728, 'Asia', 'default'),
('KEN', 'Kenya', 'Kenya', 37489, 669, 24334, 12486, 44, 54040099, 'Afrika', 'default'),
('KGZ', 'Kyrgyzstan', 'Kirgizstan', 45932, 1063, 42147, 2722, 24, 6548902, 'Asia', 'default'),
('KHM', 'Cambodia', 'Kamboja', 275, 0, 274, 1, 0, 16772360, 'Asia', 'default'),
('KNA', 'Saint Kitts and Nevis', 'Saint Kitts dan Nevis', 19, 0, 17, 2, 0, 53286, 'Amerika Utara', 'default'),
('KOR', 'S. Korea', 'Korea Selatan', 23455, 395, 20978, 2082, 128, 51279620, 'Asia', 'default'),
('KWT', 'Kuwait', 'Kuwait', 101851, 592, 92961, 8298, 101, 4285118, 'Asia', 'default'),
('LAO', 'Laos', 'Laos', 23, 0, 22, 1, 0, 651378, 'Asia', 'default'),
('LBN', 'Lebanon', 'Lebanon', 32819, 329, 14112, 18378, 139, 6818148, 'Asia', 'default'),
('LBR', 'Liberia', 'Liberia', 1338, 82, 1221, 35, 0, 5084576, 'Afrika', 'default'),
('LBY', 'Libya', 'Libya', 30632, 474, 16842, 13316, 0, 6892864, 'Afrika', 'default'),
('LCA', 'Saint Lucia', 'Saint Lucia', 27, 0, 26, 1, 0, 183822, 'Amerika Utara', 'default'),
('LIE', 'Liechtenstein', 'Liechtenstein', 116, 1, 110, 5, 0, 38153, 'Eropa', 'default'),
('LKA', 'Sri Lanka', 'Sri Lanka', 3333, 13, 3142, 178, 0, 21434354, 'Asia', 'default'),
('LSO', 'Lesotho', 'Lesotho', 1558, 35, 797, 726, 0, 2146213, 'Afrika', 'default'),
('LTU', 'Lithuania', 'Lituania', 4070, 89, 2253, 1728, 0, 2713177, 'Eropa', 'default'),
('LUX', 'Luxembourg', 'Luksemburg', 8090, 124, 6862, 1104, 1, 628289, 'Eropa', 'default'),
('LVA', 'Latvia', 'Latvia', 1594, 36, 1248, 310, 0, 1881220, 'Eropa', 'default'),
('MAC', 'Macao', 'Makau', 46, 0, 46, 0, 0, 651378, 'Asia', 'default'),
('MAF', 'Saint Martin', 'Saint Martin', 367, 8, 273, 86, 11, 38815, 'Amerika Utara', 'default'),
('MAF', 'Sint Maarten', 'Sint Maarten (Prancis)', 616, 21, 517, 78, 6, 42988, 'Amerika Utara', 'default'),
('MAR', 'Morocco', 'Maroko', 110099, 1956, 90186, 17957, 289, 37011915, 'Afrika', 'default'),
('MCO', 'Monaco', 'Monako', 199, 1, 165, 33, 1, 39306, 'Eropa', 'default'),
('MDA', 'Moldova', 'Moldova', 48232, 1244, 36071, 10917, 564, 4031759, 'Eropa', 'default'),
('MDG', 'Madagascar', 'Madagaskar', 16221, 228, 14867, 1126, 11, 27851325, 'Afrika', 'default'),
('MDV', 'Maldives', 'Maladewa', 9939, 34, 8597, 1308, 12, 542724, 'Asia', 'default'),
('MEX', 'Mexico', 'Meksiko', 710049, 74949, 510237, 124863, 2677, 129243704, 'Amerika Utara', 'default'),
('MLI', 'Mali', 'Mali', 3041, 130, 2391, 520, 0, 20381309, 'Afrika', 'default'),
('MLT', 'Malta', 'Malta', 2898, 27, 2191, 680, 0, 441817, 'Eropa', 'default'),
('MMR', 'Myanmar', 'Myanmar', 8515, 155, 2381, 5979, 0, 54495142, 'Asia', 'default'),
('MNE', 'Montenegro', 'Montenegro', 9428, 151, 5728, 3549, 0, 628085, 'Eropa', 'default'),
('MNG', 'Mongolia', 'Mongolia', 313, 0, 303, 10, 1, 3290418, 'Asia', 'default'),
('MOZ', 'Mozambique', 'Mozambik', 7399, 51, 4558, 2790, 0, 31451650, 'Afrika', 'default'),
('MRT', 'Mauritania', 'Mauritania', 7433, 161, 7052, 220, 3, 4677146, 'Afrika', 'default'),
('MSR', 'Montserrat', 'Montserrat', 13, 1, 12, 0, 0, 4993, 'Amerika Utara', 'default'),
('MTQ', 'Martinique', 'Martinik', 1290, 20, 98, 1172, 9, 375197, 'Amerika Utara', 'default'),
('MUS', 'Mauritius', 'Mauritius', 367, 10, 343, 14, 0, 1272266, 'Afrika', 'default'),
('MWI', 'Malawi', 'Malawi', 5747, 179, 4163, 1405, 4, 19241237, 'Afrika', 'default'),
('MYS', 'Malaysia', 'Malaysia', 10576, 133, 9666, 777, 6, 32461889, 'Asia', 'default'),
('MYT', 'Mayotte', 'Mayotte', 3541, 40, 2964, 537, 2, 274302, 'Afrika', 'default'),
('NAM', 'Namibia', 'Namibia', 10740, 119, 8482, 2139, 15, 2551433, 'Afrika', 'default'),
('NCL', 'New Caledonia', 'Kaledonia Baru', 26, 0, 26, 0, 0, 286129, 'Oceania', 'default'),
('NER', 'Niger', 'Niger', 1194, 69, 1107, 18, 0, 24399460, 'Afrika', 'default'),
('NGA', 'Nigeria', 'Nigeria', 57849, 1102, 49098, 7649, 7, 207292385, 'Afrika', 'default'),
('NIC', 'Nicaragua', 'Nikaragua', 5073, 149, 2913, 2011, 0, 6642596, 'Amerika Utara', 'default'),
('NLD', 'Netherlands', 'Belanda', 100597, 6296, -1, -1, 104, 17143713, 'Eropa', 'default'),
('NOR', 'Norway', 'Norwegia', 13277, 267, 10371, 2639, 2, 5431023, 'Eropa', 'default'),
('NPL', 'Nepal', 'Nepal', 69301, 453, 50411, 18437, 0, 29256737, 'Asia', 'default'),
('NUL1', 'Caribbean Netherlands', 'Karibia Belanda', 69, 1, 21, 47, 0, 26279, 'Amerika Utara', 'default'),
('NUL2', 'Channel Islands', 'Kepulauan Channel', 654, 48, 575, 31, 2, 174232, 'Eropa', 'default'),
('NUL3', 'North Macedonia', 'Macedonia Utara', 17049, 710, 14186, 2153, 3, 2083354, 'Eropa', 'default'),
('NZL', 'New Zealand', 'Selandia Baru', 1827, 25, 1737, 65, 3, 5002100, 'Oceania', 'default'),
('OMN', 'Oman', 'Oman', 95907, 885, 86765, 8257, 185, 5135890, 'Asia', 'default'),
('PAK', 'Pakistan', 'Pakistan', 309015, 6444, 294740, 7831, 544, 221871028, 'Asia', 'default'),
('PAN', 'Panama', 'Panama', 107990, 2291, 84437, 21262, 122, 4330196, 'Amerika Utara', 'default'),
('PER', 'Peru', 'Peru', 782695, 31870, 636489, 114336, 1381, 33076571, 'Amerika Selatan', 'default'),
('PHL', 'Philippines', 'Filipina', 296755, 5127, 231928, 59700, 1758, 109918002, 'Asia', 'default'),
('PNG', 'Papua New Guinea', 'Papua Nugini', 527, 7, 232, 288, 0, 8985270, 'Oceania', 'default'),
('POL', 'Poland', 'Polandia', 82809, 2369, 66158, 14282, 91, 37836886, 'Eropa', 'default'),
('PRT', 'Portugal', 'Portugal', 70465, 1928, 46290, 18586, 506, 19207285, 'Eropa', 'default'),
('PRY', 'Paraguay', 'Paraguay', 35571, 727, 19867, 14977, 157, 7152579, 'Amerika Selatan', 'default'),
('PSE', 'Palestine', 'Palestina', 37591, 274, 26934, 10383, 0, 5128259, 'Asia', 'default'),
('PYF', 'French Polynesia', 'Polinesia Prancis', 1469, 5, 1237, 227, 5, 281284, 'Oceania', 'default'),
('QAT', 'Qatar', 'Qatar', 124425, 212, 121263, 2950, 62, 2807805, 'Asia', 'default'),
('REU', 'Réunion', 'Réunion', 3501, 11, 2482, 1008, 0, 896806, 'Afrika', 'default'),
('ROU', 'Romania', 'Romania', 118054, 4591, 94877, 18586, 506, 19207285, 'Eropa', 'default'),
('RUS', 'Russia', 'Rusia', 1128836, 19948, 929829, 179059, 2300, 145949102, 'Eropa', 'default'),
('RWA', 'Rwanda', 'Rwanda', 4789, 27, 3050, 1712, 0, 13024661, 'Afrika', 'default'),
('SAU', 'Saudi Arabia', 'Arab Saudi', 331857, 4599, 314793, 12465, 1090, 34938554, 'Asia', 'default'),
('SDN', 'Sudan', 'Sudan', 13592, 836, 6764, 5992, 0, 44080967, 'Afrika', 'default'),
('SEN', 'Senegal', 'Senegal', 14816, 304, 11818, 2694, 24, 16843152, 'Afrika', 'default'),
('SGP', 'Singapore', 'Singapura', 57654, 27, 57333, 294, 0, 5861084, 'Asia', 'default'),
('SLE', 'Sierra Leone', 'Sierra Leone', 2188, 72, 1666, 450, 0, 8013928, 'Afrika', 'default'),
('SLV', 'El Salvador', 'El Salvador', 28201, 823, 22326, 5052, 89, 6493797, 'Amerika Utara', 'default'),
('SMR', 'San Marino', 'San Marino', 723, 42, 669, 12, 3, 33948, 'Eropa', 'default'),
('SOM', 'Somalia', 'Somalia', 3465, 98, 2877, 490, 0, 15992604, 'Afrika', 'default'),
('SPM', 'Saint Pierre Miquelon', 'Saint Pierre dan Miquelon', 16, 0, 6, 10, 0, 5787, 'Amerika Utara', 'default'),
('SRB', 'Serbia', 'Serbia', 33080, 744, 31536, 800, 22, 8729071, 'Eropa', 'default'),
('SSD', 'South Sudan', 'Sudan Selatan', 2669, 49, 1290, 1330, 0, 11224136, 'Afrika', 'default'),
('STP', 'Sao Tome and Principe', 'Sao Tome dan Principe', 910, 15, 881, 14, 0, 220089, 'Afrika', 'default'),
('SUR', 'Suriname', 'Suriname', 4779, 101, 4560, 118, 5, 587842, 'Amerika Selatan', 'default'),
('SVK', 'Slovakia', 'Slowakia', 7629, 41, 3978, 3610, 25, 5460261, 'Eropa', 'default'),
('SVN', 'Slovenia', 'Slovenia', 4694, 143, 3168, 1383, 20, 2079005, 'Eropa', 'default'),
('SWE', 'Sweden', 'Swedia', 89756, 5876, -1, -1, 15, 10113840, 'Eropa', 'default'),
('SWZ', 'Eswatini', 'Swaziland', 5375, 108, 4724, 543, 11, 1162955, 'Afrika', 'default'),
('SYC', 'Seychelles', 'Seychelles', 143, 0, 140, 3, 0, 98490, 'Afrika', 'default'),
('SYR', 'Syria', 'Suriah', 3966, 183, 1013, 2770, 0, 17596682, 'Asia', 'default'),
('TBO', 'Togo', 'Togo', 1707, 180, 5032, 8093, 74, 11847346, 'Afrika', 'default'),
('TCA', 'Turks and Caicos', 'Kepulauan Turks dan Caicos', 676, 5, 588, 83, 3, 38837, 'Amerika Utara', 'default'),
('TCD', 'Chad', 'Chad', 1171, 82, 1003, 86, 0, 16531324, 'Afrika', 'default'),
('THA', 'Thailand', 'Thailand', 3519, 59, 3360, 100, 1, 69841277, 'Asia', 'default'),
('TJK', 'Tajikistan', 'Tajikistan', 9520, 74, 8296, 1150, 0, 9586218, 'Asia', 'default'),
('TLS', 'Timor-Leste', 'Timor Leste', 27, 0, 27, 0, 0, 1324179, 'Asia', 'default'),
('TTO', 'Trinidad and Tobago', 'Trinidad dan Tobago', 4136, 67, 1960, 2109, 11, 1400543, 'Amerika Utara', 'default'),
('TUN', 'Tunisia', 'Tunisia', 13305, 180, 5032, 8093, 74, 11847346, 'Afrika', 'default'),
('TUR', 'Turkey', 'Turki', 309790, 7785, 271964, 30041, 1573, 84549763, 'Asia', 'default'),
('TWN', 'Taiwan', 'Taiwan', 509, 7, 480, 22, 0, 23826952, 'Asia', 'default'),
('TZA', 'Tanzania', 'Tanzania', 509, 21, 183, 305, 7, 60115081, 'Afrika', 'default'),
('UGA', 'Uganda', 'Uganda', 7218, 71, 3611, 3536, 0, 46062181, 'Afrika', 'default'),
('UKR', 'Ukraine', 'Ukraina', 188106, 3757, 83458, 100891, 177, 43671581, 'Eropa', 'default'),
('URY', 'Uruguay', 'Uruguay', 1946, 47, 1661, 238, 2, 3476531, 'Amerika Selatan', 'default'),
('USA', 'USA', 'Amerika Serikat', 7140137, 206598, 4399996, 2533543, 14086, 331452210, 'Amerika Utara', 'default'),
('UZB', 'Uzbekistan', 'Uzbekistan', 53966, 447, 50441, 3078, 283, 33580993, 'Asia', 'default'),
('VAT', 'Vatican City', 'Vatikan', 12, 0, 12, 0, 0, 801, 'Eropa', 'default'),
('VCT', 'St. Vincent Grenadines', 'Saint Vincent dan Grenadines', 64, 0, 64, 0, 0, 111022, 'Amerika Utara', 'default'),
('VEN', 'Venezuela', 'Venezuela', 69439, 574, 58759, 10106, 146, 28416979, 'Amerika Selatan', 'default'),
('VGB', 'British Virgin Islands', 'Kepulauan Virgin Britania Raya', 69, 1, 48, 20, 2, 30278, 'Amerika Utara', 'default'),
('VNM', 'Vietnam', 'Vietnam', 1069, 35, 991, 43, 0, 97542604, 'Asia', 'default'),
('WORLD', '.World.', '.Dunia.', 32138014, 982722, 23708311, 7446981, 62385, 781423273, 'null', 'default'),
('YEM', 'Yemen', 'Yaman', 2029, 586, 1250, 193, 0, 29975045, 'Asia', 'default'),
('ZAF', 'South Africa', 'Afrika Selatan', 667049, 16283, 595916, 54850, 539, 59481669, 'Afrika', 'default'),
('ZMB', 'Zambia', 'Zambia', 14491, 332, 13643, 516, 0, 18499324, 'Afrika', 'default'),
('ZWE', 'Zimbabwe', 'Zimbabwe', 7752, 227, 6043, 1482, 0, 14912782, 'Afrika', 'default');

-- --------------------------------------------------------

--
-- Struktur dari tabel `islogin`
--

CREATE TABLE `islogin` (
  `username` varchar(30) NOT NULL,
  `namalengkap` varchar(50) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(30) NOT NULL,
  `type` enum('admin','user') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `islogin`
--

INSERT INTO `islogin` (`username`, `namalengkap`, `email`, `password`, `type`) VALUES
('baihaqi', 'Achmad Baihaqi', 'hakiahmad756@gmail.com', '12345678', 'user');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `username` varchar(30) NOT NULL,
  `namalengkap` varchar(50) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(30) NOT NULL,
  `type` enum('admin','user') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`username`, `namalengkap`, `email`, `password`, `type`) VALUES
('admin1', 'Achmad Baihaqi', 'baihaqi.myapps@gmail.com', '12345', 'admin'),
('baihaqi', 'Achmad Baihaqi', 'hakiahmad756@gmail.com', '12345678', 'user'),
('dbases', 'mypass123', 'databases@gmailcom', 'MYPASS123', 'user'),
('myjdbc', 'my databases', 'my databases@gmailcom', 'MY DATABASES', 'user');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `bck_covid19cases`
--
ALTER TABLE `bck_covid19cases`
  ADD PRIMARY KEY (`eng_country`);

--
-- Indeks untuk tabel `covid19cases`
--
ALTER TABLE `covid19cases`
  ADD PRIMARY KEY (`code`,`eng_country`,`idn_country`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`,`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

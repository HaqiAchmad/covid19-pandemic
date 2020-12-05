-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 27 Nov 2020 pada 15.28
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
-- Database: `app_covid19tester`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `islogin`
--

CREATE TABLE `islogin` (
  `username` varchar(30) NOT NULL,
  `namalengkap` varchar(50) NOT NULL,
  `email` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `islogin`
--

INSERT INTO `islogin` (`username`, `namalengkap`, `email`) VALUES
('baihaqi', 'Achmad Baihaqi', 'hakiahmad756@gmail.com');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kasuscovid_dunia`
--

CREATE TABLE `kasuscovid_dunia` (
  `negara_idn` varchar(35) NOT NULL,
  `negara_eng` varchar(35) NOT NULL,
  `kasus` int(11) NOT NULL,
  `kematian` int(11) NOT NULL,
  `sembuh` int(11) NOT NULL,
  `aktif` int(11) NOT NULL,
  `kritis` int(11) NOT NULL,
  `populasi` int(11) NOT NULL,
  `diubah` date NOT NULL,
  `benua` enum('Asia','Afrika','Amerika Selatan','Amerika Utara','Eropa','Oceania','null') NOT NULL,
  `bendera` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kasuscovid_dunia`
--

INSERT INTO `kasuscovid_dunia` (`negara_idn`, `negara_eng`, `kasus`, `kematian`, `sembuh`, `aktif`, `kritis`, `populasi`, `diubah`, `benua`, `bendera`) VALUES
('Afganistan', 'Afghanistan', 39170, 1451, 32619, 5100, 93, 39124705, '2020-09-30', 'Asia', 'bendera-afganistan.jpg'),
('Afrika Selatan', 'South Africa', 667049, 16283, 595916, 54850, 539, 59481669, '2020-09-30', 'Afrika', 'bendera-afrika-selatan.png'),
('Albania', 'Albania', 12787, 370, 7139, 5278, 18, 2877060, '2020-09-30', 'Eropa', 'bendera-albania.jpg'),
('Aljazair', 'Algeria', 50400, 1698, 35428, 13274, 31, 44030062, '2020-09-30', 'Afrika', 'bendera-aljazair.png'),
('Amerika Serikat', 'USA', 7140137, 206598, 4399996, 2533543, 14086, 331452210, '2020-09-30', 'Amerika Utara', 'bendera-amerika-serikat.jpg'),
('Andorra', 'Andorra', 1753, 53, 1203, 497, 4, 77294, '2020-09-30', 'Eropa', 'bendera-andorra.png'),
('Angola', 'Angola', 4475, 162, 1503, 2810, 8, 33093817, '2020-09-30', 'Afrika', 'bendera-angola.gif'),
('Anguilla', 'Anguilla', 3, 0, 3, 0, 0, 15034, '2020-09-30', 'Amerika Utara', 'bendera-anguilla.png'),
('Antigua dan Barbuda', 'Antigua and Barbuda', 97, 3, 92, 2, 0, 98116, '2020-09-30', 'Amerika Utara', 'bendera-antigua-dan-barbuda.png'),
('Arab Saudi', 'Saudi Arabia', 331857, 4599, 314793, 12465, 1090, 34938554, '2020-09-30', 'Asia', 'bendera-arab-saudi.jpg'),
('Argentina', 'Argentina', 664799, 14376, 525486, 124937, 3511, 45291222, '2020-09-30', 'Amerika Selatan', 'bendera-argentina.jpg'),
('Armenia', 'Armenia', 48251, 945, 43266, 4040, 0, 2964535, '2020-09-30', 'Asia', 'bendera-armenia.png'),
('Aruba', 'Aruba', 3721, 25, 2501, 1195, 11, 106871, '2020-09-30', 'Amerika Utara', 'bendera-aruba.png'),
('Australia', 'Australia', 26980, 861, 24448, 1671, 10, 25567653, '2020-09-30', 'Oceania', 'bendera-australia.jpg'),
('Austria', 'Austria', 40816, 783, 31661, 8372, 78, 9018304, '2020-09-30', 'Eropa', 'bendera-austria.jpg'),
('Azerbaijan', 'Azerbaijan', 39686, 581, 37255, 1850, 0, 10160466, '2020-09-30', 'Asia', 'bendera-azerbaijan.png'),
('Bahama', 'Bahamas', 3618, 80, 1915, 1623, 79, 394108, '2020-09-30', 'Amerika Utara', 'bendera-bahama.png'),
('Bahrain', 'Bahrain', 67701, 231, 60853, 6617, 59, 1714632, '2020-09-30', 'Asia', 'bendera-bahrain.png'),
('Bangladesh', 'Bangladesh', 355384, 5072, 265092, 85220, 0, 165070897, '2020-09-30', 'Asia', 'bendera-bangladesh.png'),
('Barbados', 'Barbados', 189, 7, 174, 8, 0, 287457, '2020-09-30', 'Amerika Utara', 'bendera-barbados.png'),
('Belanda', 'Netherlands', 100597, 6296, -1, -1, 104, 17143713, '2020-09-30', 'Eropa', 'bendera-belanda.jpg'),
('Belarus', 'Belarus', 76651, 802, 73733, 2116, 0, 9448595, '2020-09-30', 'Eropa', 'bendera-belarus.png'),
('Belgia', 'Belgium', 106887, 9959, 19079, 77849, 95, 11601339, '2020-09-30', 'Eropa', 'bendera-belgia.jpg'),
('Belize', 'Belize', 1706, 22, 1019, 665, 3, 399260, '2020-09-30', 'Amerika Utara', 'bendera-belize.png'),
('Benin', 'Benin', 2325, 40, 1954, 331, 0, 12194632, '2020-09-30', 'Afrika', 'bendera-benin.png'),
('Bermuda', 'Bermuda', 181, 9, 48, 20, 0, 62224, '2020-09-30', 'Amerika Utara', 'bendera-bermuda.svg.png'),
('Bhutan', 'Bhutan', 261, 0, 196, 65, 0, 773580, '2020-09-30', 'Asia', 'bendera-bhutan.png'),
('Bolivia', 'Bolivia', 131990, 7731, 91556, 32703, 71, 11709345, '2020-09-30', 'Amerika Selatan', 'bendera-bolivia.png'),
('Bosnia dan Herzegovina', 'Bosnia and Herzegovina', 26081, 790, 18634, 6657, 0, 3275988, '2020-09-30', 'Eropa', 'bendera-bosnia-and-herzegovina.jpg'),
('Bostwana', 'Botswana', 2921, 16, 701, 2204, 1, 2362444, '2020-09-30', 'Afrika', 'bendera-botswana.png'),
('Brasil', 'Brazil', 4627780, 139065, 3992886, 495829, 8318, 212908548, '2020-09-30', 'Amerika Selatan', 'bendera-brasil.jpg'),
('Britania Raya', 'UK', 409729, 41862, -1, -1, 211, 67968701, '2020-09-30', 'Eropa', 'bendera-inggris.jpg'),
('Brunei Darussalam', 'Brunei', 146, 3, 142, 1, 0, 438454, '2020-09-30', 'Asia', 'bendera-brunei-darussalam.png'),
('Bulgaria', 'Bulgaria', 19283, 779, 13867, 4637, 29, 6936034, '2020-09-30', 'Eropa', 'bendera-bulgaria.png'),
('Burkina Faso', 'Burkina Faso', 1950, 56, 1260, 634, 0, 21031875, '2020-09-30', 'Afrika', 'bendera-burkino-faso.png'),
('Burundi', 'Burundi', 477, 1, 462, 14, 0, 11969834, '2020-09-30', 'Afrika', 'bendera-burundi.jpg'),
('Cayman Kepulauan', 'Cayman Islands', 210, 1, 205, 4, 0, 65899, '2020-09-30', 'Amerika Utara', 'bendera-cayman_islands.svg.png'),
('Chad', 'Chad', 1171, 82, 1003, 86, 0, 16531324, '2020-09-30', 'Afrika', 'bendera-chad.png'),
('Chili', 'Chile', 449903, 12345, 425165, 12393, 907, 19154009, '2020-09-30', 'Amerika Selatan', 'bendera-chili.jpg'),
('Cina', 'China', 85322, 4634, 80522, 166, 4, 1439323776, '2020-09-30', 'Asia', 'bendera-republik-rakyat-china.jpg'),
('Curaçao', 'Curaçao', 301, 1, 104, 196, 1, 164249, '2020-09-30', 'Amerika Utara', 'bendera-curacao.svg.png'),
('Denmark', 'Denmark', 24916, 645, 18646, 5625, 14, 5796947, '2020-09-30', 'Eropa', 'bendera-denmark.jpg'),
('Djibouti', 'Djibouti', 5407, 61, 5339, 7, 0, 991311, '2020-09-30', 'Afrika', 'bendera-djibouti.png'),
('Dominika', 'Dominica', 24, 0, 18, 6, 0, 72028, '2020-09-30', 'Amerika Utara', 'bendera-dominika.png'),
('Dunia', 'World', 32138014, 982722, 23708311, 7446981, 62385, 781423273, '2020-09-30', 'null', 'default'),
('Ekuador', 'Ecuador', 129892, 11171, 102852, 15869, 360, 17703980, '2020-09-30', 'Amerika Selatan', 'bendera-ekuador.jpg'),
('El Salvador', 'El Salvador', 28201, 823, 22326, 5052, 89, 6493797, '2020-09-30', 'Amerika Utara', 'bendera-el-salvador.png'),
('Eritrea', 'Eritrea', 369, 0, 330, 39, 0, 3557748, '2020-09-30', 'Afrika', 'bendera-eritrea.jpg'),
('Estonia', 'Estonia', 3076, 64, 2395, 617, 2, 1326744, '2020-09-30', 'Eropa', 'bendera-estonia.png'),
('Ethiopia', 'Ethiopia', 71687, 1148, 29461, 41078, 267, 115606131, '2020-09-30', 'Afrika', 'bendera-etiopia.png'),
('Fiji', 'Fiji', 32, 2, 28, 2, 1, 897946, '2020-09-30', 'Oceania', 'bendera-fiji.png'),
('Filipina', 'Philippines', 296755, 5127, 231928, 59700, 1758, 109918002, '2020-09-30', 'Asia', 'bendera-filipina1.jpg'),
('Finlandia', 'Finland', 9379, 343, 7850, 1186, 4, 5542730, '2020-09-30', 'Eropa', 'bendera-finlandia.png'),
('Gabon', 'Gabon', 8716, 54, 7906, 756, 2, 2237614, '2020-09-30', 'Afrika', 'bendera-gabon.png'),
('Gambia', 'Gambia', 3552, 110, 2012, 1430, 0, 2431879, '2020-09-30', 'Afrika', 'bendera-gambia.png'),
('Georgia', 'Georgia', 4399, 26, 1705, 2668, 0, 3987347, '2020-09-30', 'Asia', 'bendera-georgia.png'),
('Ghana', 'Ghana', 46222, 299, 45417, 506, 6, 31220495, '2020-09-30', 'Afrika', 'bendera-ghana.jpg'),
('Gibraltar', 'Gibraltar', 361, 0, 331, 30, 0, 33689, '2020-09-30', 'Eropa', 'bendera-gibraltar.svg.png'),
('Greenland', 'Greenland', 14, 0, 14, 0, 0, 56793, '2020-09-30', 'Amerika Utara', 'bendera-greenland.svg.png'),
('Grenada', 'Grenada', 24, 0, 24, 0, 0, 56793, '2020-09-30', 'Amerika Utara', 'bendera-grenada.png'),
('Guadeloupe', 'Guadeloupe', 4487, 42, 2199, 2246, 24, 400140, '2020-09-30', 'Amerika Utara', 'bendera-guadeloupe.svg.png'),
('Guatemala', 'Guatemala', 87442, 3154, 76459, 7829, 5, 17990424, '2020-09-30', 'Amerika Utara', 'bendera-guatemala.jpg'),
('Guinea', 'Guinea', 10434, 65, 9801, 568, 24, 13212769, '2020-09-30', 'Afrika', 'bendera-guinea.png'),
('Guinea Khatulistiwa', 'Equatorial Guinea', 5018, 83, 4530, 405, 4, 1413204, '2020-09-30', 'Afrika', 'bendera-guinea-khatulistiwa.png'),
('Guinea-Bissau', 'Guinea-Bissau', 2324, 39, 1549, 736, 5, 1978522, '2020-09-30', 'Afrika', 'bendera-guinea-bissau.png'),
('Guyana', 'Guyana', 2535, 69, 1464, 1002, 14, 787433, '2020-09-30', 'Amerika Selatan', 'bendera-guyana.png'),
('Guyana Prancis', 'French Guiana', 9762, 65, 9431, 266, 6, 300403, '2020-09-30', 'Amerika Selatan', 'bendera-guyana.png'),
('Haiti', 'Haiti', 8646, 225, 6551, 1870, 0, 11434330, '2020-09-30', 'Amerika Utara', 'bendera-haiti.png'),
('Honduras', 'Honduras', 72675, 2222, 24022, 46431, 20, 9940379, '2020-09-30', 'Amerika Utara', 'bendera-honduras.jpg'),
('Hong Kong', 'Hong Kong', 5057, 104, 4758, 195, 13, 7511174, '2020-09-30', 'Asia', 'bendera-hong_kong.svg.png'),
('Hungaria', 'Hungary', 21200, 709, 4818, 15673, 32, 9654581, '2020-09-30', 'Eropa', 'bendera-hongaria.jpg'),
('India', 'India', 5816103, 92317, 4752991, 970795, 8944, 1383159972, '2020-09-30', 'Asia', 'bendera-india.png'),
('Indonesia', 'Indonesia', 262022, 10105, 191853, 60064, -1, 274195355, '2020-09-30', 'Asia', 'bendera-indonesia.jpg'),
('Iran', 'Iran', 436319, 25015, 367829, 43475, 3957, 84241549, '2020-09-30', 'Asia', 'bendera-iran.jpg'),
('Iraq', 'Iraq', 337106, 8799, 268761, 59546, 527, 40427156, '2020-09-30', 'Asia', 'bendera-irak.jpg'),
('Irlandia', 'Ireland', 33675, 1794, 23364, 8517, 16, 4950431, '2020-09-30', 'Eropa', 'bendera-irlandia.png'),
('Islandia', 'Iceland', 2512, 10, 2150, 352, 0, 341755, '2020-09-30', 'Eropa', 'bendera-islandia.jpg'),
('Israel', 'Israel', 214458, 1378, 152294, 60786, 669, 9197590, '2020-09-30', 'Asia', 'bendera-palestina.png'),
('Italia', 'Italy', 302537, 35758, 220665, 46114, 244, 60440954, '2020-09-30', 'Eropa', 'bendera-italia.jpg'),
('Jamaika', 'Jamaica', 5395, 76, 1444, 3875, 7, 2964169, '2020-09-30', 'Amerika Utara', 'bendera-jamaika.png'),
('Jepang', 'Japan', 80041, 1520, 72538, 5983, 166, 126384252, '2020-09-30', 'Asia', 'bendera-jepang.jpg'),
('Jerman', 'Germany', 279205, 9508, 249500, 20197, 293, 83846303, '2020-09-30', 'Eropa', 'bendera-jerman.jpg'),
('Kaledonia Baru', 'New Caledonia', 26, 0, 26, 0, 0, 286129, '2020-09-30', 'Oceania', 'bendera-new caledonia.png'),
('Kamboja', 'Cambodia', 275, 0, 274, 1, 0, 16772360, '2020-09-30', 'Asia', 'bendera-kamboja.jpg'),
('Kamerun', 'Cameroon', 20712, 418, 19440, 854, 30, 26694924, '2020-09-30', 'Afrika', 'bendera-kameron.jpg'),
('Kanada', 'Canada', 147756, 9243, 127788, 10725, 86, 37818373, '2020-09-30', 'Amerika Utara', 'bendera-kanada.jpg'),
('Karibia Belanda', 'Caribbean Netherlands', 69, 1, 21, 47, 0, 26279, '2020-09-30', 'Amerika Utara', 'bendera-carribean-netherlands.svg.png'),
('Kazakhstan', 'Kazakhstan', 107590, 1699, 102360, 3531, 221, 18828728, '2020-09-30', 'Asia', 'bendera-kazakhstan.jpg'),
('Kenya', 'Kenya', 37489, 669, 24334, 12486, 44, 54040099, '2020-09-30', 'Afrika', 'bendera-kenya.png'),
('Kepulauan Channel', 'Channel Islands', 654, 48, 575, 31, 2, 174232, '2020-09-30', 'Eropa', 'bendera-channel island.png'),
('Kepulauan Falkland', 'Falkland Islands', 13, 0, 13, 0, 0, 3502, '2020-09-30', 'Amerika Selatan', 'bendera-falkland_islands.svg.png'),
('Kepulauan Foroe', 'Faeroe Islands', 451, 0, 416, 35, 0, 48906, '2020-09-30', 'Eropa', 'bendera-faroe_islands.svg.png'),
('Kepulauan Turks dan Caicos', 'Turks and Caicos', 676, 5, 588, 83, 3, 38837, '2020-09-30', 'Amerika Utara', 'bendera-turks_and_caicos_islands.svg.png'),
('Kepulauan Virgin Britania Raya', 'British Virgin Islands', 69, 1, 48, 20, 2, 30278, '2020-09-30', 'Amerika Utara', 'bendera-british_virgin_islands.svg.png'),
('Kirgizstan', 'Kyrgyzstan', 45932, 1063, 42147, 2722, 24, 6548902, '2020-09-30', 'Asia', 'bendera-kirgizstan.png'),
('Kolombia', 'Colombia', 784268, 24746, 662277, 97245, 863, 51007347, '2020-09-30', 'Amerika Selatan', 'bendera-kolumbia.png'),
('Komoro', 'Comoros', 474, 7, 453, 14, 0, 873811, '2020-09-30', 'Afrika', 'bendera-komoro.png'),
('Kongo', 'Congo', 5005, 89, 3887, 1029, 0, 5548744, '2020-09-30', 'Afrika', 'bendera-republik-kongo.png'),
('Korea Selatan', 'S. Korea', 23455, 395, 20978, 2082, 128, 51279620, '2020-09-30', 'Asia', 'bendera-korea.jpg'),
('Kosta Rika', 'Costa Rica', 68059, 781, 26136, 41142, 249, 5104825, '2020-09-30', 'Amerika Utara', 'bendera-kostarika.jpg'),
('Kroasia', 'Croatia', 15572, 261, 14111, 1200, 24, 4099274, '2020-09-30', 'Eropa', 'bendera-krosia.jpg'),
('Kuba', 'Cuba', 5270, 118, 4582, 570, 8, 11324995, '2020-09-30', 'Amerika Utara', 'bendera-kuba.png'),
('Kuwait', 'Kuwait', 101851, 592, 92961, 8298, 101, 4285118, '2020-09-30', 'Asia', 'bendera-kuwait.jpg'),
('Laos', 'Laos', 23, 0, 22, 1, 0, 651378, '2020-09-30', 'Asia', 'bendera-laos.jpg'),
('Latvia', 'Latvia', 1594, 36, 1248, 310, 0, 1881220, '2020-09-30', 'Eropa', 'bendera-latvia.png'),
('Lebanon', 'Lebanon', 32819, 329, 14112, 18378, 139, 6818148, '2020-09-30', 'Asia', 'bendera-lebanon.png'),
('Lesotho', 'Lesotho', 1558, 35, 797, 726, 0, 2146213, '2020-09-30', 'Afrika', 'bendera-lesotho.png'),
('Liberia', 'Liberia', 1338, 82, 1221, 35, 0, 5084576, '2020-09-30', 'Afrika', 'bendera-liberia.jpg'),
('Libya', 'Libya', 30632, 474, 16842, 13316, 0, 6892864, '2020-09-30', 'Afrika', 'bendera-libya.jpg'),
('Liechtenstein', 'Liechtenstein', 116, 1, 110, 5, 0, 38153, '2020-09-30', 'Eropa', 'bendera-liechtenstein.jpg'),
('Lituania', 'Lithuania', 4070, 89, 2253, 1728, 0, 2713177, '2020-09-30', 'Eropa', 'bendera-lithuania.png'),
('Luksemburg', 'Luxembourg', 8090, 124, 6862, 1104, 1, 628289, '2020-09-30', 'Eropa', 'bendera-luxembourg.jpg'),
('Macedonia Utara', 'North Macedonia', 17049, 710, 14186, 2153, 3, 2083354, '2020-09-30', 'Eropa', 'bendera-north_macedonia.svg.png'),
('Madagaskar', 'Madagascar', 16221, 228, 14867, 1126, 11, 27851325, '2020-09-30', 'Afrika', 'bendera-madagascar.jpg'),
('Makau', 'Macao', 46, 0, 46, 0, 0, 651378, '2020-09-30', 'Asia', 'bendera-macau.svg.png'),
('Maladewa', 'Maldives', 9939, 34, 8597, 1308, 12, 542724, '2020-09-30', 'Asia', 'bendera-maladewa.jpg'),
('Malawi', 'Malawi', 5747, 179, 4163, 1405, 4, 19241237, '2020-09-30', 'Afrika', 'bendera-malawi.jpg'),
('Malaysia', 'Malaysia', 10576, 133, 9666, 777, 6, 32461889, '2020-09-30', 'Asia', 'bendera-malaysia1.jpg'),
('Mali', 'Mali', 3041, 130, 2391, 520, 0, 20381309, '2020-09-30', 'Afrika', 'bendera-mali.png'),
('Malta', 'Malta', 2898, 27, 2191, 680, 0, 441817, '2020-09-30', 'Eropa', 'bendera-malta.jpg'),
('Maroko', 'Morocco', 110099, 1956, 90186, 17957, 289, 37011915, '2020-09-30', 'Afrika', 'bendera-maroko.jpg'),
('Martinik', 'Martinique', 1290, 20, 98, 1172, 9, 375197, '2020-09-30', 'Amerika Utara', 'bendera-martinique.svg.png'),
('Mauritania', 'Mauritania', 7433, 161, 7052, 220, 3, 4677146, '2020-09-30', 'Afrika', 'bendera-mauritania.png'),
('Mauritius', 'Mauritius', 367, 10, 343, 14, 0, 1272266, '2020-09-30', 'Afrika', 'bendera-mauritius.png'),
('Mayotte', 'Mayotte', 3541, 40, 2964, 537, 2, 274302, '2020-09-30', 'Afrika', 'bendera-mayotte.png'),
('Meksiko', 'Mexico', 710049, 74949, 510237, 124863, 2677, 129243704, '2020-09-30', 'Amerika Utara', 'bendera-meksiko.jpg'),
('Mesir', 'Egypt', 102513, 5835, 92644, 4034, 41, 102775319, '2020-09-30', 'Afrika', 'bendera-mesir.png'),
('Moldova', 'Moldova', 48232, 1244, 36071, 10917, 564, 4031759, '2020-09-30', 'Eropa', 'bendera-moldova.png'),
('Monako', 'Monaco', 199, 1, 165, 33, 1, 39306, '2020-09-30', 'Eropa', 'bendera-monako.png'),
('Mongolia', 'Mongolia', 313, 0, 303, 10, 1, 3290418, '2020-09-30', 'Asia', 'bendera-mongolia.png'),
('Montenegro', 'Montenegro', 9428, 151, 5728, 3549, 0, 628085, '2020-09-30', 'Eropa', 'bendera-montenegro.png'),
('Montserrat', 'Montserrat', 13, 1, 12, 0, 0, 4993, '2020-09-30', 'Amerika Utara', 'bendera-montserrat.svg.png'),
('Mozambik', 'Mozambique', 7399, 51, 4558, 2790, 0, 31451650, '2020-09-30', 'Afrika', 'bendera-mozambik.jpg'),
('Myanmar', 'Myanmar', 8515, 155, 2381, 5979, 0, 54495142, '2020-09-30', 'Asia', 'bendera-myanmar.jpg'),
('Namibia', 'Namibia', 10740, 119, 8482, 2139, 15, 2551433, '2020-09-30', 'Afrika', 'bendera-namibia.png'),
('Nepal', 'Nepal', 69301, 453, 50411, 18437, 0, 29256737, '2020-09-30', 'Asia', 'bendera-nepal.png'),
('Niger', 'Niger', 1194, 69, 1107, 18, 0, 24399460, '2020-09-30', 'Afrika', 'bendera-niger.jpg'),
('Nigeria', 'Nigeria', 57849, 1102, 49098, 7649, 7, 207292385, '2020-09-30', 'Afrika', 'bendera-nigeria.jpg'),
('Nikaragua', 'Nicaragua', 5073, 149, 2913, 2011, 0, 6642596, '2020-09-30', 'Amerika Utara', 'bendera-nikaragua.png'),
('Norwegia', 'Norway', 13277, 267, 10371, 2639, 2, 5431023, '2020-09-30', 'Eropa', 'bendera-norwegia.jpg'),
('Oman', 'Oman', 95907, 885, 86765, 8257, 185, 5135890, '2020-09-30', 'Asia', 'bendera-oman.png'),
('Pakistan', 'Pakistan', 309015, 6444, 294740, 7831, 544, 221871028, '2020-09-30', 'Asia', 'bendera-pakistan.png'),
('Palestina', 'Palestine', 37591, 274, 26934, 10383, 0, 5128259, '2020-09-30', 'Asia', 'bendera-palestina.png'),
('Panama', 'Panama', 107990, 2291, 84437, 21262, 122, 4330196, '2020-09-30', 'Amerika Utara', 'bendera-panama.jpg'),
('Pantai Gading', 'Ivory Coast', 19501, 120, 19003, 378, 0, 26525664, '2020-09-30', 'Afrika', 'bendera-pantai-gading.jpg'),
('Papua Nugini', 'Papua New Guinea', 527, 7, 232, 288, 0, 8985270, '2020-09-30', 'Oceania', 'bendera-papua-nugini.jpg'),
('Paraguay', 'Paraguay', 35571, 727, 19867, 14977, 157, 7152579, '2020-09-30', 'Amerika Selatan', 'bendera-paraguay.png'),
('Peru', 'Peru', 782695, 31870, 636489, 114336, 1381, 33076571, '2020-09-30', 'Amerika Selatan', 'bendera-peru.gif'),
('Polandia', 'Poland', 82809, 2369, 66158, 14282, 91, 37836886, '2020-09-30', 'Eropa', 'bendera-polandia.jpg'),
('Polinesia Prancis', 'French Polynesia', 1469, 5, 1237, 227, 5, 281284, '2020-09-30', 'Oceania', 'bendera-french_polynesia.svg.png'),
('Portugal', 'Portugal', 70465, 1928, 46290, 18586, 506, 19207285, '2020-09-30', 'Eropa', 'bendera-portugal.jpg'),
('Prancis', 'France', 481141, 31459, 93538, 356144, 951, 65307193, '2020-09-30', 'Eropa', 'bendera-perancis.png'),
('Pulau Man', 'Isle of Man', 340, 24, 312, 4, 0, 85137, '2020-09-30', 'Eropa', 'bendera-isle_of_man.svg.png'),
('Qatar', 'Qatar', 124425, 212, 121263, 2950, 62, 2807805, '2020-09-30', 'Asia', 'bendera-qatar.jpg'),
('Republik Afrika Tengah', 'CAR', 4804, 62, 1837, 2905, 2, 4849008, '2020-09-30', 'Afrika', 'bendera-republik-afrika-tengah.png'),
('Republik Ceko', 'Czechia', 55464, 555, 26709, 28200, 120, 10713617, '2020-09-30', 'Eropa', 'bendera-republik-ceko.jpg'),
('Republik Demokratik Kongo', 'DRC', 10555, 271, 10051, 233, 0, 90168360, '2020-09-30', 'Afrika', 'bendera-demokratik-republik-kongo.jpg'),
('Republik Dominika', 'Dominican Republic', 109737, 2074, 83434, 24229, 208, 10872904, '2020-09-30', 'Amerika Utara', 'bendera-republik-dominika.png'),
('Réunion', 'Réunion', 3501, 11, 2482, 1008, 0, 896806, '2020-09-30', 'Afrika', 'bendera-reounion.png'),
('Romania', 'Romania', 118054, 4591, 94877, 18586, 506, 19207285, '2020-09-30', 'Eropa', 'bendera-rumania.jpg'),
('Rusia', 'Russia', 1128836, 19948, 929829, 179059, 2300, 145949102, '2020-09-30', 'Eropa', 'bendera-rusia.jpg'),
('Rwanda', 'Rwanda', 4789, 27, 3050, 1712, 0, 13024661, '2020-09-30', 'Afrika', 'bendera-rwanda.png'),
('Sahara Barat', 'Western Sahara', 10, 1, 8, 1, 0, 600654, '2020-09-30', 'Afrika', 'bendera-sahara barat.png'),
('Saint Barthélemy', 'St. Barth', 45, 0, 25, 20, 0, 9884, '2020-09-30', 'Amerika Utara', 'bendera-saint_barthelemy_(local).svg.png'),
('Saint Kitts dan Nevis', 'Saint Kitts and Nevis', 19, 0, 17, 2, 0, 53286, '2020-09-30', 'Amerika Utara', 'bendera-saint-kitts-dan-nevis.jpg'),
('Saint Lucia', 'Saint Lucia', 27, 0, 26, 1, 0, 183822, '2020-09-30', 'Amerika Utara', 'bendera-saint-lucia.png'),
('Saint Martin', 'Saint Martin', 367, 8, 273, 86, 11, 38815, '2020-09-30', 'Amerika Utara', 'bendera-saint-vincent-and-the-grenadines.png'),
('Saint Pierre dan Miquelon', 'Saint Pierre Miquelon', 16, 0, 6, 10, 0, 5787, '2020-09-30', 'Amerika Utara', 'bendera-saint-pierre_and_miquelon.svg.png'),
('Saint Vincent dan Grenadines', 'St. Vincent Grenadines', 64, 0, 64, 0, 0, 111022, '2020-09-30', 'Amerika Utara', 'bendera-sint_maarten.svg.png'),
('San Marino', 'San Marino', 723, 42, 669, 12, 3, 33948, '2020-09-30', 'Eropa', 'bendera-san-marino.jpg'),
('Sao Tome dan Principe', 'Sao Tome and Principe', 910, 15, 881, 14, 0, 220089, '2020-09-30', 'Afrika', 'bendera-sao-tome-and-principe.png'),
('Selandia Baru', 'New Zealand', 1827, 25, 1737, 65, 3, 5002100, '2020-09-30', 'Oceania', 'bendera-selandia-baru-new-zealand.png'),
('Senegal', 'Senegal', 14816, 304, 11818, 2694, 24, 16843152, '2020-09-30', 'Afrika', 'bendera-senegal.png'),
('Serbia', 'Serbia', 33080, 744, 31536, 800, 22, 8729071, '2020-09-30', 'Eropa', 'bendera-serbia.jpg'),
('Seychelles', 'Seychelles', 143, 0, 140, 3, 0, 98490, '2020-09-30', 'Afrika', 'bendera-seychelles.png'),
('Sierra Leone', 'Sierra Leone', 2188, 72, 1666, 450, 0, 8013928, '2020-09-30', 'Afrika', 'bendera-sierra-leone.png'),
('Singapura', 'Singapore', 57654, 27, 57333, 294, 0, 5861084, '2020-09-30', 'Asia', 'bendera-singapura.jpg'),
('Sint Maarten (Prancis)', 'Sint Maarten', 616, 21, 517, 78, 6, 42988, '2020-09-30', 'Amerika Utara', 'bendera-sint_maarten.svg.png'),
('Siprus', 'Cyprus', 1663, 22, 1369, 272, 1, 1209413, '2020-09-30', 'Asia', 'bendera-siprus.png'),
('Slovenia', 'Slovenia', 4694, 143, 3168, 1383, 20, 2079005, '2020-09-30', 'Eropa', 'bendera-slovenia.png'),
('Slowakia', 'Slovakia', 7629, 41, 3978, 3610, 25, 5460261, '2020-09-30', 'Eropa', 'bendera-slowakia.jpg'),
('Somalia', 'Somalia', 3465, 98, 2877, 490, 0, 15992604, '2020-09-30', 'Afrika', 'bendera-somalia.png'),
('Spanyol', 'Spain', 693556, 31034, -1, -1, 1436, 46759015, '2020-09-30', 'Eropa', 'bendera-spanyol.jpg'),
('Sri Lanka', 'Sri Lanka', 3333, 13, 3142, 178, 0, 21434354, '2020-09-30', 'Asia', 'bendera-srilanka.jpg'),
('Sudan', 'Sudan', 13592, 836, 6764, 5992, 0, 44080967, '2020-09-30', 'Afrika', 'bendera-sudan.png'),
('Sudan Selatan', 'South Sudan', 2669, 49, 1290, 1330, 0, 11224136, '2020-09-30', 'Afrika', 'bendera-sudan-selatan.png'),
('Suriah', 'Syria', 3966, 183, 1013, 2770, 0, 17596682, '2020-09-30', 'Asia', 'bendera-suriah.png'),
('Suriname', 'Suriname', 4779, 101, 4560, 118, 5, 587842, '2020-09-30', 'Amerika Selatan', 'bendera-suriname.png'),
('Swaziland', 'Eswatini', 5375, 108, 4724, 543, 11, 1162955, '2020-09-30', 'Afrika', 'bendera-swaziland.png'),
('Swedia', 'Sweden', 89756, 5876, -1, -1, 15, 10113840, '2020-09-30', 'Eropa', 'bendera-swedia.jpg'),
('Swiss', 'Switzerland', 51492, 2061, 42300, 7131, 34, 8669241, '2020-09-30', 'Eropa', 'bendera-swiss.gif'),
('Taiwan', 'Taiwan', 509, 7, 480, 22, 0, 23826952, '2020-09-30', 'Asia', 'bendera-taiwan.png'),
('Tajikistan', 'Tajikistan', 9520, 74, 8296, 1150, 0, 9586218, '2020-09-30', 'Asia', 'bendera-tajikistan.png'),
('Tanjung Verde', 'Cabo Verde', 5479, 55, 4917, 507, 0, 557389, '2020-09-30', 'Afrika', 'bendera-tanjung-verde.png'),
('Tanzania', 'Tanzania', 509, 21, 183, 305, 7, 60115081, '2020-09-30', 'Afrika', 'bendera-tanzania.png'),
('Thailand', 'Thailand', 3519, 59, 3360, 100, 1, 69841277, '2020-09-30', 'Asia', 'bendera-thailand.jpg'),
('Timor Leste', 'Timor-Leste', 27, 0, 27, 0, 0, 1324179, '2020-09-30', 'Asia', 'bendera-timor-leste.png'),
('Togo', 'Togo', 1707, 180, 5032, 8093, 74, 11847346, '2020-09-30', 'Afrika', 'bendera-togo.png'),
('Trinidad dan Tobago', 'Trinidad and Tobago', 4136, 67, 1960, 2109, 11, 1400543, '2020-09-30', 'Amerika Utara', 'bendera-trinidad-and-tobago.png'),
('Tunisia', 'Tunisia', 13305, 180, 5032, 8093, 74, 11847346, '2020-09-30', 'Afrika', 'bendera-tunisia.jpg'),
('Turki', 'Turkey', 309790, 7785, 271964, 30041, 1573, 84549763, '2020-09-30', 'Asia', 'bendera-turki.jpg'),
('Uganda', 'Uganda', 7218, 71, 3611, 3536, 0, 46062181, '2020-09-30', 'Afrika', 'bendera-uganda.png'),
('Ukraina', 'Ukraine', 188106, 3757, 83458, 100891, 177, 43671581, '2020-09-30', 'Eropa', 'bendera-ukraina.jpg'),
('Uni Emirat Arab', 'UAE', 88532, 407, 77937, 10188, 0, 9918074, '2020-09-30', 'Asia', 'bendera-uni-emirate-arab.png'),
('Uruguay', 'Uruguay', 1946, 47, 1661, 238, 2, 3476531, '2020-09-30', 'Amerika Selatan', 'bendera-uruguay.jpg'),
('Uzbekistan', 'Uzbekistan', 53966, 447, 50441, 3078, 283, 33580993, '2020-09-30', 'Asia', 'bendera-uzbekistan.png'),
('Vatikan', 'Vatican City', 12, 0, 12, 0, 0, 801, '2020-09-30', 'Eropa', 'bendera-vatikan.jpg'),
('Venezuela', 'Venezuela', 69439, 574, 58759, 10106, 146, 28416979, '2020-09-30', 'Amerika Selatan', 'bendera-venezuela.jpg'),
('Vietnam', 'Vietnam', 1069, 35, 991, 43, 0, 97542604, '2020-09-30', 'Asia', 'bendera-vietnam.jpg'),
('Yaman', 'Yemen', 2029, 586, 1250, 193, 0, 29975045, '2020-09-30', 'Asia', 'bendera-yaman.png'),
('Yordania', 'Jordan', 6591, 36, 3937, 2618, 13, 10226688, '2020-09-30', 'Asia', 'bendera-yordania.png'),
('Yunani', 'Greece', 16286, 357, 9989, 5940, 73, 10411029, '2020-09-30', 'Eropa', 'bendera-yunani.jpg'),
('Zambia', 'Zambia', 14491, 332, 13643, 516, 0, 18499324, '2020-09-30', 'Afrika', 'bendera-zambia.png'),
('Zimbabwe', 'Zimbabwe', 7752, 227, 6043, 1482, 0, 14912782, '2020-09-30', 'Afrika', 'bendera-zimbabwe1.jpg');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kasuscovid_indo`
--

CREATE TABLE `kasuscovid_indo` (
  `kode` varchar(10) NOT NULL,
  `provinsi` varchar(40) NOT NULL,
  `kasus` int(11) NOT NULL,
  `sembuh` int(11) NOT NULL,
  `kematian` int(11) NOT NULL,
  `aktif` int(11) NOT NULL,
  `total_kab` int(5) NOT NULL,
  `kab_zonamerah` int(5) NOT NULL,
  `kab_zonaoranye` int(5) NOT NULL,
  `kab_zonahijau` int(5) NOT NULL,
  `kasus_pertama` date NOT NULL,
  `diubah` date NOT NULL,
  `website` varchar(50) NOT NULL,
  `lambang` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kasuscovid_indo`
--

INSERT INTO `kasuscovid_indo` (`kode`, `provinsi`, `kasus`, `sembuh`, `kematian`, `aktif`, `total_kab`, `kab_zonamerah`, `kab_zonaoranye`, `kab_zonahijau`, `kasus_pertama`, `diubah`, `website`, `lambang`) VALUES
('Aceh', 'Aceh', 8244, 6580, 318, 1346, 18, 0, 0, 0, '2020-03-26', '2020-11-27', 'covid19.acehprov.go.id', 'lambang-aceh.png'),
('Babel', 'Kep. Bangka Belitung', 944, 784, 12, 148, 6, 0, 0, 0, '2020-03-30', '2020-11-27', 'covid19.babelprov.go.id', 'lambang-bangka-belitung.png'),
('Bali', 'Bali', 13500, 12267, 419, 814, 8, 0, 0, 0, '2020-03-10', '2020-11-27', 'infocorona.baliprov.go.id', 'lambang-bali.png'),
('Banten', 'Banten', 12223, 8396, 318, 3509, 4, 0, 0, 0, '2020-03-08', '2020-11-27', 'infocorona.bantenprov.go.id', 'lambang-banten.png'),
('Bengkulu', 'Bengkulu', 1665, 1287, 73, 305, 9, 0, 0, 0, '2020-03-31', '2020-11-27', 'covid19.bengkuluprov.go.id', 'lambang-bengkulu.png'),
('DIY', 'DI Yogyakarta', 5556, 4200, 137, 1219, 4, 0, 0, 0, '2020-03-13', '2020-11-27', 'corona.jogjaprov.go.id', 'lambang-yogyakarta.png'),
('Gorontalo', 'Gorontalo', 3073, 2956, 89, 28, 5, 0, 0, 0, '2020-04-09', '2020-11-27', 'covid-19.gorontaloprov.go.id', 'lambang-gorontalo.jpeg'),
('Jabar', 'Jawa Barat', 50482, 41932, 882, 7668, 18, 0, 0, 0, '2020-03-02', '2020-11-27', 'pikobar.jabarprov.go.id', 'lambang-jawa-barat.png'),
('Jakarta', 'DKI Jakarta', 131525, 120194, 2592, 8739, 1, 0, 0, 0, '2020-03-02', '2020-11-27', 'corona.jakarta.go.id', 'lambang-jakarta.png'),
('Jambi', 'Jambi', 1774, 1263, 36, 475, 9, 0, 0, 0, '2020-03-23', '2020-11-27', 'corona.jambiprov.go.id', 'lambang-jambi.jpeg'),
('Jateng', 'Jawa Tengah', 50880, 36681, 2197, 12002, 29, 0, 0, 0, '2020-03-09', '2020-11-27', 'corona.jatengprov.go.id', 'lambang-jawa-tengah.png'),
('Jatim', 'Jawa Timur', 60190, 53131, 4275, 2784, 29, 0, 0, 0, '2020-03-17', '2020-11-27', 'infocovid19.jatimprov.go.id', 'lambang-jawa-timur.png'),
('Kalbar', 'Kalimanan Barat', 2322, 1825, 22, 475, 12, 0, 0, 0, '2020-03-10', '2020-11-27', 'covid19.kalbarprov.go.id', 'lambang-kalimantan-barat.png'),
('Kalsel', 'Kalimantan Selatan', 13047, 11859, 522, 666, 11, 0, 0, 0, '2020-03-22', '2020-11-27', 'corona.kalselprov.go.id', 'lambang-kalimantan-selatan.gif'),
('Kaltara', 'Kalimantan Utara', 1320, 921, 17, 382, 4, 0, 0, 0, '2020-03-28', '2020-11-27', 'coronainfo.kaltaraprov.go.id', 'lambang-kalimantan-utara.png'),
('Kalteng', 'Kalimantan Tengah', 5603, 4526, 185, 892, 13, 0, 0, 0, '2020-03-20', '2020-11-27', 'corona.kalteng.go.id', 'lambang-kalimantan-tengah.png'),
('Kaltim', 'Kalimantan Timur', 18916, 16255, 577, 2084, 7, 0, 0, 0, '2020-03-18', '2020-11-17', 'covid19.kaltimprov.go.id', 'lambang-kalimantan-timur.png'),
('Kepri', 'Kepulauan Riau', 5304, 3998, 133, 1173, 5, 0, 0, 0, '2020-03-17', '2020-11-27', 'corona.kepriprov.go.id', 'lambang-kepulauan-riau.png'),
('Lampung', 'Lampung', 3340, 1757, 135, 1448, 13, 0, 0, 0, '2020-03-18', '2020-11-27', 'covid19.lampungprov.go.id', 'lambang-lampung.png'),
('Maluku', 'Maluku', 4428, 3755, 60, 613, 9, 0, 0, 0, '2020-03-22', '2020-11-17', 'corona.malukuprov.go.id', 'lambang-maluku.png'),
('Malut', 'Maluku Utara', 2356, 1971, 81, 304, 8, 0, 0, 0, '2020-03-23', '2020-11-27', 'corona.malutprov.go.id', 'lambang-maluku-utara.png'),
('NTB', 'Nusa Tenggara Barat', 4577, 3687, 237, 653, 8, 0, 0, 0, '2020-03-24', '2020-11-27', 'corona.ntbprov.go.id', 'lambang-nusa-tenggara-barat.png'),
('NTT', 'Nusa Tenggara Timur', 1077, 663, 17, 397, 21, 0, 0, 0, '2020-04-09', '2020-11-27', 'covid19.nttprov.go.id', 'lambang-nusa-tenggara-timur.png'),
('Pabar', 'Papua Barat', 5204, 4513, 82, 609, 12, 0, 0, 0, '2020-03-27', '2020-11-27', 'dinkes.papuabaratprov.go.id', 'lambang-papua-barat.png'),
('Papua', 'Papua', 9934, 5007, 137, 4790, 28, 0, 0, 0, '2020-03-22', '2020-11-27', 'covid19.papua.go.id', 'lambang-papua.png'),
('Riau', 'Riau', 19335, 16769, 428, 2138, 10, 0, 0, 0, '2020-03-18', '2020-11-27', 'corona.riau.go.id', 'lambang-riau.png'),
('Sulbar', 'Sulawesi Barat', 1404, 1241, 21, 142, 6, 0, 0, 0, '2020-03-29', '2020-11-27', 'dinkes.sulbarprov.go.id', 'lambang-sulawesi-barat.png'),
('Sulsel', 'Sulawesi Selatan', 20206, 18208, 490, 1508, 21, 0, 0, 0, '2020-03-19', '2020-11-27', 'covid19.sulselprov.go.id', 'lambang-sulawesi-selatan.png'),
('Sulteng', 'Sulawesi Tengah', 1700, 1016, 68, 616, 12, 0, 0, 0, '2020-03-26', '2020-11-27', 'dinkes.sultengprov.go.id', 'lambang-sulawesi-tengah.png'),
('Sultra', 'Sulawesi Tenggara', 6265, 5057, 97, 1111, 15, 0, 0, 0, '2020-03-19', '2020-11-27', 'dinkes.sultraprov.go.id', 'lambang-sulawesi-tenggara.png'),
('Sulut', 'Sulawesi Utara', 6594, 5211, 239, 1144, 11, 0, 0, 0, '2020-03-14', '2020-11-27', 'corona.sulutprov.go.id', 'lambang-sulawesi-utara.png'),
('Sumbar', 'Sumatra Barat', 19251, 15638, 363, 3250, 12, 0, 0, 0, '2020-03-26', '2020-11-27', 'corona.sumbarprov.go.id', 'lambang-sumatra-barat.png'),
('Sumsel', 'Sumatra Selatan', 9300, 7504, 494, 1302, 13, 0, 0, 0, '2020-03-24', '2020-11-27', 'corona.sumselprov.go.id', 'lambang-sumatra-selatan.png'),
('Sumut', 'Sumatra Utara', 15235, 12601, 600, 2034, 25, 0, 0, 0, '2020-03-18', '2020-11-27', 'corona.sulutprov.go.id', 'lambang-sumatra-utara.png');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `username` varchar(30) NOT NULL,
  `namalengkap` varchar(50) NOT NULL,
  `namapanggilan` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `gender` enum('L','P','N') NOT NULL,
  `tgl_lahir` date NOT NULL,
  `pekerjaan` varchar(30) NOT NULL,
  `alamat` varchar(35) NOT NULL,
  `negara` varchar(35) NOT NULL,
  `password` varchar(30) NOT NULL,
  `tgl_dibuat` date NOT NULL,
  `fotoprofile` text NOT NULL,
  `type` enum('Admin','User','Guest') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`username`, `namalengkap`, `namapanggilan`, `email`, `gender`, `tgl_lahir`, `pekerjaan`, `alamat`, `negara`, `password`, `tgl_dibuat`, `fotoprofile`, `type`) VALUES
('aderaihan', 'Ade Raihan Mahsa', 'Raihan', 'aderaihanacaaca@gmail.com', 'L', '2002-11-19', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'aderaihan', '2020-11-18', 'default', 'User'),
('agung', 'Agung Tri Laksono', 'Agung', 'agungtrilaksono1287@gmail.com', 'L', '2003-12-13', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'agungtri', '2020-11-18', 'default', 'User'),
('ananta', 'Ananta Eka Prayoga', 'Ananta', 'anantaprayoga25@gmail.com', 'L', '2002-01-28', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'ananta1234', '2020-11-18', 'default', 'User'),
('ansori', 'Ahmad Ansori', 'Ansori', 'aanblogme@gmail.com', 'L', '2002-05-01', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'ansori1234', '2020-11-18', 'default', 'User'),
('arifin', 'M. Arifin Mustofa', 'Arifin', 'arifinmmustofa173@gmail.com', 'L', '2003-10-26', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'arifin1234', '2020-11-18', 'default', 'User'),
('baihaqi', 'Achmad Baihaqi', 'Baihaqi', 'hakiahmad756@gmail.com', 'L', '2003-08-04', 'Software Enginer', 'Jawa Timur', 'Indonesia', '12345678', '2020-11-15', 'default', 'Admin'),
('baihaqi2', 'achmad baihaqi s.kom mta', 'baihaqi', 'hakiahmad@gmail.com', 'N', '0001-01-01', 'null', 'jawa timur', 'indonesia', 'mypassword123', '2020-11-26', 'default', 'User'),
('bima', 'Fahrezian Arya Bima', 'Bima', 'awal.in45@gmail.com', 'L', '2003-09-20', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'bima1234', '2020-11-18', 'default', 'User'),
('chuenk', 'M. Nur Kholis Chu Enk Yunani', 'Chu Enk', 'chuenkaisyah15@gmail.com', 'L', '2003-05-02', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'chu1234', '2020-11-18', 'default', 'User'),
('david', 'David Aldian Hidayat', 'David', 'davidaldian15@gmail.com', 'L', '2002-05-17', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'davidaldian', '2020-11-18', 'default', 'User'),
('deky', 'Deky Reza Saputra', 'Deky', 'dekyreza8787@gmail.com', 'L', '2002-11-28', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'dekyreza', '2020-11-18', 'default', 'User'),
('dhinno', 'Dhinno Haryasena', 'Dhinno', 'dhinosena@gmail.com', 'L', '2001-10-06', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'dhinno1234', '2020-11-18', 'default', 'User'),
('didin', 'Didin Rakfil Beniafan', 'Didin', 'rakfildidin@gmail.com', 'L', '2002-10-27', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'didin1234', '2020-11-18', 'default', 'User'),
('fatur', 'Fatur Riandy', 'Fatur', 'riyandifatur868@gmail.com', 'L', '2002-09-20', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'faturiandy', '2020-11-18', 'default', 'User'),
('halim', 'Halim Bagus Perdana', 'Halim', 'halimpaijo1@gmail.com', 'L', '2002-11-09', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'halimbagus', '2020-11-18', 'default', 'User'),
('haqi', 'achmad baihaqi', 'baihaqi', 'hakiahma.@gmail.com', 'N', '0000-01-01', 'null', 'jawa timur', 'indonesia', 'password', '2020-11-26', 'default', 'User'),
('iamrobot', 'tsukasa tsukuyomi', 'tsukasa', 'tsukasacha@gmail.com', 'N', '0001-01-01', 'null', 'jawa timur', 'indonesia', 'pass1235', '2020-11-26', 'default', 'User'),
('ikan', 'hanya ikan air tawar biasa', 'ikan air tawar', 'masikanlele@gmail.com', 'N', '0001-01-01', 'null', 'jawa timur', 'indonesia', 'ffffffffffffff', '2020-11-26', 'default', 'User'),
('maul', 'Ilham Maulana', 'Ilham', 'im285281@gmail.com', 'L', '2002-06-16', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'mauldesu', '2020-11-18', 'default', 'User'),
('null', 'achmad baihaqi', 'baihaqi', 'hakiahmad7@gmail.com', 'N', '0000-01-01', 'null', 'jawa timur', 'indonesia', '12345678', '2020-11-26', 'default', 'User'),
('robot', 'Achmad Baihaqi', 'Baihaqi', 'BaIhaQI@gmail.com', 'N', '0001-01-01', 'null', 'jawa timur', 'indonesia', 'privatevoid', '2020-11-26', 'default', 'User'),
('robotai', 'artificial inteligenge', 'artificial', 'airobotic2020@gmail.com', 'N', '0000-01-01', 'null', 'jawa timur', 'indonesia', 'machine1234', '2020-11-26', 'default', 'User'),
('rozikul', 'Ahmad Rozikul Akbar', 'Rozikul', 'ahmadrozikul76@gmail.com', 'L', '2002-03-26', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'rozikulakbar', '2020-11-18', 'default', 'User'),
('tesaja', 'baihaqi', 'baihaqi', 'baihaqi.myapps@gmail.com', 'N', '0001-01-01', 'null', 'jawa timur', 'indonesia', 'appjava20', '2020-11-26', 'default', 'User'),
('tester', 'aku hanya ikan', 'hanya ikan', 'ikanlele@gmail.com', 'N', '0001-01-01', 'null', 'jawa timur', 'indonesia', 'HANYA IKAN', '2020-11-26', 'default', 'User');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `kasuscovid_dunia`
--
ALTER TABLE `kasuscovid_dunia`
  ADD PRIMARY KEY (`negara_idn`,`negara_eng`);

--
-- Indeks untuk tabel `kasuscovid_indo`
--
ALTER TABLE `kasuscovid_indo`
  ADD PRIMARY KEY (`kode`,`provinsi`,`website`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`,`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
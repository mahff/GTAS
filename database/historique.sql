-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  jeu. 21 mars 2019 à 10:50
-- Version du serveur :  8.0.13-4
-- Version de PHP :  7.2.15-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bHykARFGFP`
--

-- --------------------------------------------------------

--
-- Structure de la table `historique`
--

CREATE TABLE `historique` (
  `id` int(11) NOT NULL,
  `id_etudiant` varchar(30) DEFAULT NULL,
  `cours` int(11) DEFAULT NULL,
  `arrivee` datetime DEFAULT NULL,
  `debut` datetime DEFAULT NULL,
  `fin` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `historique`
--

INSERT INTO `historique` (`id`, `id_etudiant`, `cours`, `arrivee`, `debut`, `fin`) VALUES
(9, '21701326', 4, '2019-03-07 12:25:00', '2019-03-07 12:30:00', '2019-03-07 14:30:00'),
(10, '21206834', 4, '2019-03-07 12:30:00', '2019-03-07 12:30:00', '2019-03-07 14:30:00'),
(11, '21701091', 4, '2019-03-07 12:45:00', '2019-03-07 12:30:00', '2019-03-07 14:30:00'),
(12, '21206834', 4, '2019-03-08 12:35:00', '2019-03-08 12:30:00', '2019-03-08 14:30:00'),
(16, '21700247', 4, '2019-03-08 12:30:00', '2019-03-08 12:30:00', '2019-03-08 14:30:00'),
(41, '21704400', 4, NULL, '2019-03-07 12:30:00', '2019-03-07 14:30:00'),
(42, '21603042', 4, NULL, '2019-03-07 12:30:00', '2019-03-07 14:30:00'),
(43, '21604442', 4, NULL, '2019-03-07 12:30:00', '2019-03-07 14:30:00'),
(44, '21700247', 4, NULL, '2019-03-07 12:30:00', '2019-03-07 14:30:00'),
(57, '21714587', 4, '2019-03-07 12:25:00', '2019-03-07 12:30:00', '2019-03-07 14:30:00'),
(58, '21712674', 4, '2019-03-07 12:20:00', '2019-03-07 12:30:00', '2019-03-07 14:30:00'),
(59, '21705957', 4, '2019-03-07 12:35:00', '2019-03-07 12:30:00', '2019-03-07 14:30:00'),
(60, '21702382', 4, '2019-03-07 12:34:00', '2019-03-07 12:30:00', '2019-03-07 14:30:00'),
(61, '21206834', 2, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(62, '21603042', 2, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(63, '21604442', 2, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(64, '21700247', 2, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(65, '21701091', 2, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(66, '21701326', 2, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(67, '21702382', 2, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(68, '21704400', 2, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(69, '21705957', 2, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(70, '21712674', 2, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(71, '21714587', 2, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(72, '21706154', 2, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(73, '21707028', 7, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(74, '21718829', 7, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(75, '21814532', 7, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(76, '21909403', 7, '2019-03-08 08:29:00', '2019-03-08 08:30:00', '2019-03-08 12:30:00'),
(78, '21718829', 7, '2019-03-12 11:30:00', '2019-03-12 08:30:00', '2019-03-12 11:50:00'),
(79, '21701091', 7, '2019-03-12 11:32:00', '2019-03-12 08:30:00', '2019-03-12 11:50:00'),
(80, '21702993', 7, '2019-03-12 11:33:00', '2019-03-12 08:30:00', '2019-03-12 11:50:00'),
(81, '21701091', 2, '2019-03-12 13:04:00', '2019-03-12 13:30:00', '2019-03-12 13:50:00'),
(83, '21701326', 4, NULL, '2019-03-08 12:30:00', '2019-03-08 14:30:00'),
(84, '21701380', 4, '2019-03-09 12:30:00', '2019-03-09 12:30:00', '2019-03-09 14:30:00'),
(85, '21701091', 4, '2019-03-09 12:45:00', '2019-03-09 12:30:00', '2019-03-09 14:30:00'),
(89, '21701380', 4, '2019-03-10 12:30:00', '2019-03-10 12:30:00', '2019-03-10 14:30:00'),
(90, '21701091', 4, '2019-03-10 12:45:00', '2019-03-10 12:30:00', '2019-03-10 14:30:00'),
(93, '21701326', 2, '2019-03-21 08:20:00', '2019-03-21 08:30:00', '2019-03-21 12:30:00'),
(94, '21701091', 2, '2019-03-21 08:25:00', '2019-03-21 08:30:00', '2019-03-21 12:30:00'),
(95, '21700247', 2, '2019-03-21 08:25:00', '2019-03-21 08:30:00', '2019-03-21 12:30:00'),
(96, '21712674', 2, '2019-03-21 08:25:00', '2019-03-21 08:30:00', '2019-03-21 12:30:00'),
(97, '21705957', 2, '2019-03-21 08:25:00', '2019-03-21 08:30:00', '2019-03-21 12:30:00'),
(98, '21702382', 2, '2019-03-21 08:25:00', '2019-03-21 08:30:00', '2019-03-21 12:30:00'),
(99, '21706154', 2, '2019-03-21 08:25:00', '2019-03-21 08:30:00', '2019-03-21 12:30:00'),
(100, '21707317', 2, '2019-03-21 08:35:00', '2019-03-21 08:30:00', '2019-03-21 12:30:00'),
(101, '21912433', 2, '2019-03-21 08:25:00', '2019-03-21 08:30:00', '2019-03-21 12:30:00'),
(102, '21701326', 2, '2019-03-22 08:20:00', '2019-03-22 08:30:00', '2019-03-22 12:30:00'),
(103, '21700247', 2, '2019-03-22 08:25:00', '2019-03-22 08:30:00', '2019-03-22 12:30:00'),
(104, '21712674', 2, '2019-03-22 08:25:00', '2019-03-22 08:30:00', '2019-03-22 12:30:00'),
(105, '21705957', 2, '2019-03-22 08:25:00', '2019-03-22 08:30:00', '2019-03-22 12:30:00'),
(106, '21702382', 2, '2019-03-22 08:25:00', '2019-03-22 08:30:00', '2019-03-22 12:30:00'),
(107, '21707317', 2, '2019-03-22 08:35:00', '2019-03-22 08:30:00', '2019-03-22 12:30:00'),
(108, '21912433', 2, '2019-03-22 08:25:00', '2019-03-22 08:30:00', '2019-03-22 12:30:00'),
(109, '21701326', 2, '2019-02-22 08:20:00', '2019-02-22 08:30:00', '2019-02-22 12:30:00'),
(110, '21700247', 2, '2019-02-22 08:25:00', '2019-02-22 08:30:00', '2019-02-22 12:30:00'),
(111, '21712674', 2, '2019-02-22 08:25:00', '2019-02-22 08:30:00', '2019-02-22 12:30:00'),
(112, '21705957', 2, '2019-02-22 08:25:00', '2019-02-22 08:30:00', '2019-02-22 12:30:00'),
(113, '21702382', 2, '2019-02-22 08:25:00', '2019-02-22 08:30:00', '2019-02-22 12:30:00'),
(114, '21707317', 2, '2019-02-22 08:35:00', '2019-02-22 08:30:00', '2019-02-22 12:30:00'),
(115, '21912433', 2, '2019-02-22 08:25:00', '2019-02-22 08:30:00', '2019-02-22 12:30:00'),
(116, '21703008', 4, NULL, '2019-03-07 12:30:00', '2019-03-07 14:30:00'),
(121, '21701091', 1, '2019-03-19 22:55:00', '2019-03-19 22:51:00', '2019-03-19 22:51:00'),
(122, '21700247', 1, '2019-03-20 09:56:00', '2019-03-20 09:55:00', '2019-03-20 09:55:00'),
(123, '21701091', 7, '2019-03-20 15:23:06', '2019-03-20 09:54:00', '2019-03-20 17:54:00'),
(124, '21701091', 7, '2019-03-20 15:23:08', '2019-03-20 09:54:00', '2019-03-20 17:54:00'),
(125, '21701091', 7, '2019-03-20 15:52:40', '2019-03-20 09:54:00', '2019-03-20 17:54:00'),
(126, '21701091', 7, '2019-03-20 15:53:25', '2019-03-20 09:54:00', '2019-03-20 17:54:00'),
(127, '21700247', 1, '2019-03-20 16:19:00', '2019-03-20 16:00:00', '2019-03-20 18:10:00'),
(128, '21701091', 1, '2019-03-20 18:06:25', '2019-03-20 17:50:00', '2019-03-20 19:04:00'),
(129, '21701091', 1, '2019-03-20 18:13:00', '2019-03-20 17:50:00', '2019-03-20 19:04:00'),
(130, '21703694', 4, NULL, '2019-03-07 12:30:00', '2019-03-07 14:30:00'),
(132, '21701091', 1, '2019-03-20 19:55:09', '2019-03-20 18:20:00', '2019-03-20 20:20:00'),
(134, '21701091', 7, '2019-03-21 08:47:55', '2019-03-21 08:45:00', '2019-03-21 08:55:00'),
(135, '21701091', 7, '2019-03-21 08:48:01', '2019-03-21 08:45:00', '2019-03-21 08:55:00'),
(136, '21701091', 1, '2019-03-21 10:45:25', '2019-03-21 10:30:00', '2019-03-21 10:55:00');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `historique`
--
ALTER TABLE `historique`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_etudiant` (`id_etudiant`),
  ADD KEY `fk_cours` (`cours`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `historique`
--
ALTER TABLE `historique`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=138;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `historique`
--
ALTER TABLE `historique`
  ADD CONSTRAINT `fk_cours` FOREIGN KEY (`cours`) REFERENCES `matiere` (`id`),
  ADD CONSTRAINT `fk_etudiant` FOREIGN KEY (`id_etudiant`) REFERENCES `etudiant` (`num_etu`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

<?php

class DB_Functions{
  private $db;
  static private $mysqli;
  function __construct() {
   define("DB_HOST", "remotemysql.com");
   define("DB_USER", "bHykARFGFP"); //id7917654_mahff
   define("DB_PASSWORD", "GkIgyv0TrD");
   define("DB_DATABASE", "bHykARFGFP"); //id7917654_gtas
   self::$mysqli = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
   if (mysqli_connect_errno()) printf("Échec de la connexion : %s\n", mysqli_connect_error());
 }

 public function getEtu($login, $password){
   $hash = sha1($password);
   $sql = "SELECT * FROM etudiant WHERE login='" . $login . "' AND password='" . $hash . "';";
   $query = mysqli_query(self::$mysqli, $sql);
   return mysqli_fetch_array($query, MYSQLI_ASSOC);
 }

 public function getProf($login, $password){
   $hash = sha1($password);
   $sql = "SELECT * FROM enseignant WHERE login='" . $login . "' AND password='" . $hash . "';";
   $query = mysqli_query(self::$mysqli, $sql);
   return mysqli_fetch_array($query, MYSQLI_ASSOC);
 }


  public function getMatiere($idProf){
    $someArray = [];
    $sql = "SELECT * FROM matiere, cours, enseignant WHERE enseignant.id_enseignant ='" . $idProf . "' AND (enseignant.id_enseignant = cours.id_enseignant AND cours.id_matiere = matiere.id)";
    $query = mysqli_query(self::$mysqli, $sql);
    while ($get = mysqli_fetch_assoc($query)) {
      array_push($someArray, [
        'id'   => $get['id'],
        'name' => $get['name']
      ]);
    }
    return $someArray;
  }

  public function scan($student, $matiere, $begin, $arrival, $end){
    $someArray = [];
    $getMatiereId = "SELECT id FROM matiere WHERE name= '".$matiere."'";
    $query = mysqli_query(self::$mysqli, $getMatiereId);
    if($query) {
      $result = mysqli_fetch_array($query, MYSQLI_ASSOC);
      $ins = $result['id'];
      $sql = "INSERT INTO historique(id, id_etudiant, cours, arrivee, debut, fin)
      VALUES (DEFAULT, '$student', '$ins', '$arrival','$begin', '$end')";
      $insert = mysqli_query(self::$mysqli, $sql);
      if($insert){
        $getData = "SELECT * FROM historique WHERE id_etudiant='" . $student . "' AND (arrivee='" . $arrival . "' AND cours = '" . $ins . "');";
        $myquery = mysqli_query(self::$mysqli, $getData);
        while ($get = mysqli_fetch_assoc($myquery)) {
          array_push($someArray, [
            'id_etudiant' => $get['id_etudiant'],
            'matiere' => $get['cours'],
            'arrival' => $get['arrivee'],
            'begin' => $get['debut']
          ]);
        }
      }
    }
    return $someArray;
  }

  public function history($student){
    $sql = "SELECT * FROM historique, matiere WHERE (historique.id_etudiant = '".$student."') && (historique.cours = matiere.id)";
    $query = mysqli_query(self::$mysqli, $sql);
    $someArray = [];
    while ($row = mysqli_fetch_assoc ($query)) {
      array_push($someArray, [
        'id_etudiant' => $row['id_etudiant'],
        'cours' => $row['name'],
        'arrivee' => $row['arrivee'],
        'debut' => $row['debut']
      ]);
    }
    return $someArray;
  }
  public function registerCourse($idUser, $course, $begin, $end, $group, $filiere){
    $someArray = [];
    $sql = "INSERT INTO course(id, id_enseignant, course, begin, end, groupe, filiere)
    VALUES (DEFAULT, '$idUser', '$course','$begin', '$end', '$group', '$filiere')";
    $insert = mysqli_query(self::$mysqli, $sql);
    if($insert){
      $getData = "SELECT * FROM course WHERE id_enseignant='" . $idUser . "' AND (begin='" . $begin . "' AND course = '" . $course . "');";
      $myquery = mysqli_query(self::$mysqli, $getData);
      while ($get = mysqli_fetch_assoc($myquery)) {
        array_push($someArray, [
          'teacher' => $get['id_enseignant'],
          'course' => $get['course'],
          'begin' => $get['begin'],
          'end' => $get['end'],
          'group' => $get['groupe'],
          'filiere' => $get['filiere']
        ]);
      }
    }
      return $someArray;
  }
}
?>

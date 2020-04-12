<?php

	class tableOperations{
		private $con;
		
		function __construct(){
			require_once dirname(__FILE__) . '/DbConnect.php';
			$db = new DbConnect;
			$this->con = $db->connect();
		}
		
		public function newTable($number, $tableStatus, $needHelp, $needRefill){
			$state = $this->con->prepare("INSERT INTO myTables (number, tableStatus, needHelp, needRefill) VALUES (?, ?, ?, ?)");
			
			$state->bind_param("isii", $number, $tableStatus, $needHelp, $needRefill);
			
			if($state->execute()){
				return TABLE_CREATE;
			}
			else{
				return TABLE_FAIL;
			}			
		}
		
		public function allTables(){
			$state = $this->con->prepare("SELECT * FROM myTables");
			$state->execute();
			$state->bind_result($number, $tableStatus, $needHelp, $needRefill);
			
			$tables = array();
			while($state->fetch()){
				$table = array();
				$table['number'] = $number;
				$table['tableStatus'] = $tableStatus;
				$table['needHelp'] = $needHelp;
				$table['needRefill'] = $needRefill;
				array_push($tables, $table);
			}
			return $tables;
		}
		
		public function findtable($number){
			$state = $this->con->prepare("SELECT * FROM myTables WHERE number = ?");
			$state->bind_param("i", $number);
			$state->execute();
			$state->bind_result($number, $tableStatus, $needHelp, $needRefill);
			$state->fetch();
			$table = array();
			$table['number'] = $number;
			$table['tableStatus'] = $tableStatus;
			$table['needHelp'] = $needHelp;
			$table['needRefill'] = $needRefill;
			return $table;
		}
		
		public function tableExist($number){
			$state = $this->con->prepare("SELECT * FROM myTables WHERE number = ?");
			$state->bind_param("i", $number);
			$state->execute();
			$state->store_result();
			if ($state->num_rows > 0) return true;
			return false;
		}
		
		public function updatetable($number, $tableStatus, $needHelp, $needRefill){
            $state = $this->con->prepare("UPDATE myTables SET tableStatus = ?, needHelp = ?, needRefill = ? WHERE number = ?");
            $state->bind_param("sdsi", $tableStatus, $needHelp, $needRefill, $number);
            if($state->execute())
                return true;
            return false; 
		}
		
        public function deletetable($number){
            $state = $this->con->prepare("DELETE FROM myTables WHERE number = ?");
            $state->bind_param("i", $number);
            if($state->execute())
                return true; 
            return false; 
        }
	}

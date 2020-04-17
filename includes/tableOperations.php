<?php

	class tableOperations{
		private $con;
		
		function __construct(){
			require_once dirname(__FILE__) . '/DbConnect.php';
			$db = new DbConnect;
			$this->con = $db->connect();
		}
		
		public function newTable($number, $status, $needHelp, $needRefill){
			$state = $this->con->prepare("INSERT INTO storeTables (number, status, needHelp, needRefill, orderTotal) VALUES (?, ?, ?, ?, ?)");
			
			$state->bind_param("isiid", $number, $status, $needHelp, $needRefill, $orderTotal);
			
			if($state->execute()){
				return TABLE_CREATE;
			}
			else{
				return TABLE_FAIL;
			}			
		}
		
		public function allTables(){
			$state = $this->con->prepare("SELECT * FROM storeTables");
			$state->execute();
			$state->bind_result($number, $status, $needHelp, $needRefill, $orderTotal);
			
			$tables = array();
			while($state->fetch()){
				$table = array();
				$table['number'] = $number;
				$table['status'] = $status;
				$table['needHelp'] = $needHelp;
				$table['needRefill'] = $needRefill;
				$table['orderTotal'] = $orderTotal;
				array_push($tables, $table);
			}
			return $tables;
		}
		
		public function findtable($number){
			$state = $this->con->prepare("SELECT * FROM storeTables WHERE number = ?");
			$state->bind_param("i", $number);
			$state->execute();
			$state->bind_result($number, $status, $needHelp, $needRefill, $orderTotal);
			$state->fetch();
			$table = array();
			$table['number'] = $number;
			$table['status'] = $status;
			$table['needHelp'] = $needHelp;
			$table['needRefill'] = $needRefill;
			$table['orderTotal'] = $orderTotal;
			return $table;
		}
		
		public function tableExist($number){
			$state = $this->con->prepare("SELECT * FROM storeTables WHERE number = ?");
			$state->bind_param("i", $number);
			$state->execute();
			$state->store_result();
			if ($state->num_rows > 0) return true;
			return false;
		}
		
		public function updatetable($number, $status, $needHelp, $needRefill, $orderTotal){
            $state = $this->con->prepare("UPDATE storeTables SET status = ?, needHelp = ?, needRefill = ?, orderTotal = ? WHERE number = ?");
            $state->bind_param("siidi", $status, $needHelp, $needRefill, $orderTotal, $number);
            if($state->execute())
                return true;
            return false; 
		}
		
        public function deletetable($number){
            $state = $this->con->prepare("DELETE FROM storeTables WHERE number = ?");
            $state->bind_param("i", $number);
            if($state->execute())
                return true; 
            return false; 
        }
	}

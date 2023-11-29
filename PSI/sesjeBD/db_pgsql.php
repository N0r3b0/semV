<?php
class db_pgsql 
{
    private $host = 'localhost';
    private $port = '5432';
    private $user = 'postgres';
    private $password = 'postgres';
    private $database = 'postgres';
    private $conn = null;
    private $status_connection = '';
    private $status;

    public function connect() {
        $conn_string = "host={$this->host} port={$this->port} dbname={$this->database} user={$this->user} password={$this->password}";
        $this->conn = pg_connect($conn_string);
        if ($this->conn) {
            $this->status = true;
        } else {
            $this->status = false;
        }
    }

    public function disconnect() {
        if ($this->conn) {
            pg_close($this->conn);
            $this->conn = null;
        }
    }

    public function query($sql) {
        if ($this->conn) {
            $result = pg_query($this->conn, $sql);
            return $result !== false;
        }
        return false;
    }

    public function getStatus() {
        return $this->status;
    }
}
?>
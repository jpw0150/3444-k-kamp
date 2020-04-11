<?php

	define('DB_HOST', 'localhost');
	define('DB_USER', 'root');
	define('DB_PASS', '');
	define('DB_NAME', 'kingdump');
	
	define('ITEM_CREATE', 101);
	define('ITEM_EXIST', 102);
	define('ITEM_FAIL', 103);
	
	define('ORDER_CREATE', 201);
	define('ORDER_FAIL', 202);
	
	define('EMP_CREATE', 301);
	define('EMP_FAIL', 303);
	define('EMP_AUTHENTICATED', 304);
	define('EMP_PASSWORD_FAIL', 305);
	define('EMP_NOT_FOUND', 306);
	
	define('USER_CREATED', 401);
    define('USER_EXISTS', 402);
    define('USER_FAILURE', 403);

    define('USER_AUTHENTICATED', 501);
    define('USER_NOT_FOUND', 502);
    define('USER_PASSWORD_DO_NOT_MATCH', 503);

    define('PASSWORD_CHANGED', 601);
    define('PASSWORD_DO_NOT_MATCH', 602);
    define('PASSWORD_NOT_CHANGED', 603);
	
	define('ING_CREATE', 701);
	define('ING_FAIL', 702);
	define('ING_EXIST', 703);
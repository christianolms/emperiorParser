/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar ExperimentParser;
file: line+;
line: timestamp subject = ('experiment'|'task') action=STRING;
//experimentLine :  timestamp action='experiment' EXPPREFIX STRING EXPPREFIX|;
//taskLine :  timestamp  action='task'  STRING;


 
 timestamp: date time;
 date: NUMBER DOT NUMBER;
 time: NUMBER COLON NUMBER COLON NUMBER DOT NUMBER;
 target: 'experiment'|'task';

 NUMBER: [0-9]+;
 DOT: '.';
 COLON: ':';
 EXPPREFIX: '---';
 WS : [ \t\r\n]+ ->skip;
 STRING: [a-z-]+;
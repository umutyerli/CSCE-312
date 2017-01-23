// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, the
// program clears the screen, i.e. writes "white" in every pixel.

// Put your code here.

    @status
    M=-1        
    D=0         
    @BLACK
    0;JMP

(LOOP)
    @KBD
    D=M         
    @BLACK
    D;JEQ       
    D=-1        
    
(BLACK)     
    @ARG
    M=D         
    @status     
    D=D-M       
    @LOOP
    D;JEQ        
    @ARG
    D=M
    @status
    M=D         
    @SCREEN
    D=A         
    @8192
    D=D+A       
    @x
    M=D         
    
(ONELOOP)    
    @x
    D=M-1
    M=D         
    @LOOP
    D;JLT       
    @status
    D=M          
    @x
    A=M         
    M=D         
    @ONELOOP
    0;JMP
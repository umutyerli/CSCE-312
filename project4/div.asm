// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Div.asm

// Divides R0 by R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

// Put your code here.

	@R0
	D=M
	@x
	M=D

	@R1
	D=M
	@y
	M=D

	@R2
	M=0

(WHILE)
	// beginning of loop cond
	@x
	D=M
	@END
	D;JLE	// if x <= 0 goto END
	// end of loop cond

	// beginning of while

	@y
	D=M
	@x
	M=M-D
	@R2
	M=M+1

	// end of while
	@WHILE
	0;JMP	// jump back to loop start
(END)
	@END
	0;JMP


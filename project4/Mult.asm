// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Do multiplication of R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

// Put your code here.

	@R0
	D=M
	@x
	M=D	// x = R0
	@count
	M=D

	@R1
	D=M
	@y
	M=D	// y = R1

	@0
	D=A
	@R2
	M=D	// R2 = 0

(WHILE)
	// begin of loop condition
	@x
	D=M	// load R0 for loop condition
	@END
	D;JLT	// if x < 0 goto END      
	// end of loop condition

	// begin body of while
	@count
	D=M
	@temp
	M=1<D 	// int temp = 1<count

	// if(R1&Temp <= 0) goto fred
    @R1
    D=M
    @temp
    D=D&M
    @IFSTATE
    D;JLE
    @count
    D=M
	@R0
	D=M<D
	@R2
	M=D+M 	// Result+=R0<<count
(IFSTATE)
	//decrement counter
	@count
	M=M-1	// x = x - 1	
	// end body of while

	@WHILE
	0;JMP	// jump to loop start
(END)	
	@END
	0;JMP	// Infinite loop (program end)
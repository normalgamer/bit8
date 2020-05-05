# bit8
Bit8 is an invented 8 bit CPU with 4K addressable memory for ROM and variables, made as a challenge so I learn Java programming.

## Work in progress
Supported opcodes
·00  -  NOP
·66  -  HLT - hang, cpu ded, F (execute order 66 >:D)
·A0  -  LDX (next hex value)(direct number)
·AA  -  LDX (absolute addr)
·B0  -  LDY (next hex value)(direct number)
·BB  -  LDY (absolute addr)
·D0  -  JMP (next 2 byte addr)
·E0  -  JSR (next 2 byte addr)
·EA  -  RTS (pcStack)

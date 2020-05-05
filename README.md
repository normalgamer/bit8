# bit8
Bit8 is an invented 8 bit CPU with 4K addressable memory for ROM and variables, made as a challenge so I learn Java programming.

## Why
Why not? I've always wanted to learn how emulators work and create mine, so I found out about the Chip 8, some sort of invented CPU/interpreter made for programmers to develop games at home. But after failing to start doing so I decided to create my own system, and now I understand how to make emulators :D !


## Work in progress
Right now, it's pretty barebones, though I'm planning on adding support for graphics, input, etc.
Supported opcodes
- 00  -  NOP
- 66  -  HLT - hang, cpu ded, F (execute order 66 >:D)
- A0  -  LDX (next hex value)(direct number)
- A1  -  STX (next 2 byte addr)
- AA  -  LDX (absolute addr)
- B0  -  LDY (next hex value)(direct number)
- B1  -  STY (next 2 byte addr)
- BB  -  LDY (absolute addr)
- D0  -  JMP (next 2 byte addr)
- E0  -  JSR (next 2 byte addr)
- EA  -  RTS (pcStack)

section .text

global _start
_start:
  mov rax, 60       ; the exit() call number
  mov rdi, 5        ; 5  // something to look at
  syscall           ; make the call


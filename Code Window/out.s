	.section	__TEXT,__text,regular,pure_instructions
	.build_version macos, 10, 14	sdk_version 10, 14
	.globl	_main                   ## -- Begin function main
	.p2align	4, 0x90
_main:                                  ## @main
	.cfi_startproc
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	movq	__ZNSt3__14coutE@GOTPCREL(%rip), %rdi
	leaq	L_.str(%rip), %rsi
	movl	$9, %edx
	callq	__ZNSt3__124__put_character_sequenceIcNS_11char_traitsIcEEEERNS_13basic_ostreamIT_T0_EES7_PKS4_m
	xorl	%eax, %eax
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
	.globl	__ZNSt3__124__put_character_sequenceIcNS_11char_traitsIcEEEERNS_13basic_ostreamIT_T0_EES7_PKS4_m ## -- Begin function _ZNSt3__124__put_character_sequenceIcNS_11char_traitsIcEEEERNS_13basic_ostreamIT_T0_EES7_PKS4_m
	.weak_def_can_be_hidden	__ZNSt3__124__put_character_sequenceIcNS_11char_traitsIcEEEERNS_13basic_ostreamIT_T0_EES7_PKS4_m
	.p2align	4, 0x90
__ZNSt3__124__put_character_sequenceIcNS_11char_traitsIcEEEERNS_13basic_ostreamIT_T0_EES7_PKS4_m: ## @_ZNSt3__124__put_character_sequenceIcNS_11char_traitsIcEEEERNS_13basic_ostreamIT_T0_EES7_PKS4_m
Lfunc_begin0:
	.cfi_startproc
	.cfi_personality 155, ___gxx_personality_v0
	.cfi_lsda 16, Lexception0
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	pushq	%r15
	pushq	%r14
	pushq	%r13
	pushq	%r12
	pushq	%rbx
	subq	$40, %rsp
	.cfi_offset %rbx, -56
	.cfi_offset %r12, -48
	.cfi_offset %r13, -40
	.cfi_offset %r14, -32
	.cfi_offset %r15, -24
	movq	%rdx, %r14
	movq	%rsi, %r15
	movq	%rdi, %rbx
Ltmp0:
	leaq	-80(%rbp), %rdi
	movq	%rbx, %rsi
	callq	__ZNSt3__113basic_ostreamIcNS_11char_traitsIcEEE6sentryC1ERS3_
Ltmp1:
## %bb.1:
	cmpb	$0, -80(%rbp)
	je	LBB1_11
## %bb.2:
	movq	(%rbx), %rax
	movq	-24(%rax), %rax
	leaq	(%rbx,%rax), %r12
	movq	40(%rbx,%rax), %rdi
	movl	8(%rbx,%rax), %r13d
	movl	144(%rbx,%rax), %eax
	cmpl	$-1, %eax
	jne	LBB1_8
## %bb.3:
Ltmp2:
	movq	%rdi, -64(%rbp)         ## 8-byte Spill
	leaq	-56(%rbp), %rdi
	movq	%r12, %rsi
	callq	__ZNKSt3__18ios_base6getlocEv
Ltmp3:
## %bb.4:                               ## %.noexc
Ltmp4:
	movq	__ZNSt3__15ctypeIcE2idE@GOTPCREL(%rip), %rsi
	leaq	-56(%rbp), %rdi
	callq	__ZNKSt3__16locale9use_facetERNS0_2idE
Ltmp5:
## %bb.5:
	movq	(%rax), %rcx
Ltmp6:
	movl	$32, %esi
	movq	%rax, %rdi
	callq	*56(%rcx)
	movb	%al, -41(%rbp)          ## 1-byte Spill
Ltmp7:
## %bb.6:                               ## %_ZNKSt3__19basic_iosIcNS_11char_traitsIcEEE5widenEc.exit.i
Ltmp12:
	leaq	-56(%rbp), %rdi
	callq	__ZNSt3__16localeD1Ev
Ltmp13:
## %bb.7:                               ## %.noexc18
	movsbl	-41(%rbp), %eax         ## 1-byte Folded Reload
	movl	%eax, 144(%r12)
	movq	-64(%rbp), %rdi         ## 8-byte Reload
LBB1_8:
	addq	%r15, %r14
	andl	$176, %r13d
	cmpl	$32, %r13d
	movq	%r15, %rdx
	cmoveq	%r14, %rdx
Ltmp14:
	movsbl	%al, %r9d
	movq	%r15, %rsi
	movq	%r14, %rcx
	movq	%r12, %r8
	callq	__ZNSt3__116__pad_and_outputIcNS_11char_traitsIcEEEENS_19ostreambuf_iteratorIT_T0_EES6_PKS4_S8_S8_RNS_8ios_baseES4_
Ltmp15:
## %bb.9:
	testq	%rax, %rax
	jne	LBB1_11
## %bb.10:
	movq	(%rbx), %rax
	movq	-24(%rax), %rax
	leaq	(%rbx,%rax), %rdi
	movl	32(%rbx,%rax), %esi
	orl	$5, %esi
Ltmp17:
	callq	__ZNSt3__18ios_base5clearEj
Ltmp18:
LBB1_11:                                ## %_ZNSt3__19basic_iosIcNS_11char_traitsIcEEE8setstateEj.exit
Ltmp22:
	leaq	-80(%rbp), %rdi
	callq	__ZNSt3__113basic_ostreamIcNS_11char_traitsIcEEE6sentryD1Ev
Ltmp23:
LBB1_21:
	movq	%rbx, %rax
	addq	$40, %rsp
	popq	%rbx
	popq	%r12
	popq	%r13
	popq	%r14
	popq	%r15
	popq	%rbp
	retq
LBB1_16:
Ltmp19:
	jmp	LBB1_17
LBB1_12:
Ltmp8:
	movq	%rax, %r14
Ltmp9:
	leaq	-56(%rbp), %rdi
	callq	__ZNSt3__16localeD1Ev
Ltmp10:
	jmp	LBB1_18
LBB1_13:
Ltmp11:
	movq	%rax, %rdi
	callq	___clang_call_terminate
LBB1_15:
Ltmp16:
LBB1_17:                                ## %.body
	movq	%rax, %r14
LBB1_18:                                ## %.body
Ltmp20:
	leaq	-80(%rbp), %rdi
	callq	__ZNSt3__113basic_ostreamIcNS_11char_traitsIcEEE6sentryD1Ev
Ltmp21:
	jmp	LBB1_19
LBB1_14:
Ltmp24:
	movq	%rax, %r14
LBB1_19:
	movq	%r14, %rdi
	callq	___cxa_begin_catch
	movq	(%rbx), %rax
	movq	%rbx, %rdi
	addq	-24(%rax), %rdi
Ltmp25:
	callq	__ZNSt3__18ios_base33__set_badbit_and_consider_rethrowEv
Ltmp26:
## %bb.20:
	callq	___cxa_end_catch
	jmp	LBB1_21
LBB1_22:
Ltmp27:
	movq	%rax, %rbx
Ltmp28:
	callq	___cxa_end_catch
Ltmp29:
## %bb.23:
	movq	%rbx, %rdi
	callq	__Unwind_Resume
	ud2
LBB1_24:
Ltmp30:
	movq	%rax, %rdi
	callq	___clang_call_terminate
Lfunc_end0:
	.cfi_endproc
	.section	__TEXT,__gcc_except_tab
	.p2align	2
GCC_except_table1:
Lexception0:
	.byte	255                     ## @LPStart Encoding = omit
	.byte	155                     ## @TType Encoding = indirect pcrel sdata4
	.uleb128 Lttbase0-Lttbaseref0
Lttbaseref0:
	.byte	1                       ## Call site Encoding = uleb128
	.uleb128 Lcst_end0-Lcst_begin0
Lcst_begin0:
	.uleb128 Ltmp0-Lfunc_begin0     ## >> Call Site 1 <<
	.uleb128 Ltmp1-Ltmp0            ##   Call between Ltmp0 and Ltmp1
	.uleb128 Ltmp24-Lfunc_begin0    ##     jumps to Ltmp24
	.byte	1                       ##   On action: 1
	.uleb128 Ltmp2-Lfunc_begin0     ## >> Call Site 2 <<
	.uleb128 Ltmp3-Ltmp2            ##   Call between Ltmp2 and Ltmp3
	.uleb128 Ltmp16-Lfunc_begin0    ##     jumps to Ltmp16
	.byte	1                       ##   On action: 1
	.uleb128 Ltmp4-Lfunc_begin0     ## >> Call Site 3 <<
	.uleb128 Ltmp7-Ltmp4            ##   Call between Ltmp4 and Ltmp7
	.uleb128 Ltmp8-Lfunc_begin0     ##     jumps to Ltmp8
	.byte	1                       ##   On action: 1
	.uleb128 Ltmp12-Lfunc_begin0    ## >> Call Site 4 <<
	.uleb128 Ltmp15-Ltmp12          ##   Call between Ltmp12 and Ltmp15
	.uleb128 Ltmp16-Lfunc_begin0    ##     jumps to Ltmp16
	.byte	1                       ##   On action: 1
	.uleb128 Ltmp17-Lfunc_begin0    ## >> Call Site 5 <<
	.uleb128 Ltmp18-Ltmp17          ##   Call between Ltmp17 and Ltmp18
	.uleb128 Ltmp19-Lfunc_begin0    ##     jumps to Ltmp19
	.byte	1                       ##   On action: 1
	.uleb128 Ltmp22-Lfunc_begin0    ## >> Call Site 6 <<
	.uleb128 Ltmp23-Ltmp22          ##   Call between Ltmp22 and Ltmp23
	.uleb128 Ltmp24-Lfunc_begin0    ##     jumps to Ltmp24
	.byte	1                       ##   On action: 1
	.uleb128 Ltmp9-Lfunc_begin0     ## >> Call Site 7 <<
	.uleb128 Ltmp10-Ltmp9           ##   Call between Ltmp9 and Ltmp10
	.uleb128 Ltmp11-Lfunc_begin0    ##     jumps to Ltmp11
	.byte	1                       ##   On action: 1
	.uleb128 Ltmp20-Lfunc_begin0    ## >> Call Site 8 <<
	.uleb128 Ltmp21-Ltmp20          ##   Call between Ltmp20 and Ltmp21
	.uleb128 Ltmp30-Lfunc_begin0    ##     jumps to Ltmp30
	.byte	1                       ##   On action: 1
	.uleb128 Ltmp21-Lfunc_begin0    ## >> Call Site 9 <<
	.uleb128 Ltmp25-Ltmp21          ##   Call between Ltmp21 and Ltmp25
	.byte	0                       ##     has no landing pad
	.byte	0                       ##   On action: cleanup
	.uleb128 Ltmp25-Lfunc_begin0    ## >> Call Site 10 <<
	.uleb128 Ltmp26-Ltmp25          ##   Call between Ltmp25 and Ltmp26
	.uleb128 Ltmp27-Lfunc_begin0    ##     jumps to Ltmp27
	.byte	0                       ##   On action: cleanup
	.uleb128 Ltmp26-Lfunc_begin0    ## >> Call Site 11 <<
	.uleb128 Ltmp28-Ltmp26          ##   Call between Ltmp26 and Ltmp28
	.byte	0                       ##     has no landing pad
	.byte	0                       ##   On action: cleanup
	.uleb128 Ltmp28-Lfunc_begin0    ## >> Call Site 12 <<
	.uleb128 Ltmp29-Ltmp28          ##   Call between Ltmp28 and Ltmp29
	.uleb128 Ltmp30-Lfunc_begin0    ##     jumps to Ltmp30
	.byte	1                       ##   On action: 1
	.uleb128 Ltmp29-Lfunc_begin0    ## >> Call Site 13 <<
	.uleb128 Lfunc_end0-Ltmp29      ##   Call between Ltmp29 and Lfunc_end0
	.byte	0                       ##     has no landing pad
	.byte	0                       ##   On action: cleanup
Lcst_end0:
	.byte	1                       ## >> Action Record 1 <<
                                        ##   Catch TypeInfo 1
	.byte	0                       ##   No further actions
	.p2align	2
                                        ## >> Catch TypeInfos <<
	.long	0                       ## TypeInfo 1
Lttbase0:
	.p2align	2
                                        ## -- End function
	.section	__TEXT,__text,regular,pure_instructions
	.private_extern	__ZNSt3__116__pad_and_outputIcNS_11char_traitsIcEEEENS_19ostreambuf_iteratorIT_T0_EES6_PKS4_S8_S8_RNS_8ios_baseES4_ ## -- Begin function _ZNSt3__116__pad_and_outputIcNS_11char_traitsIcEEEENS_19ostreambuf_iteratorIT_T0_EES6_PKS4_S8_S8_RNS_8ios_baseES4_
	.globl	__ZNSt3__116__pad_and_outputIcNS_11char_traitsIcEEEENS_19ostreambuf_iteratorIT_T0_EES6_PKS4_S8_S8_RNS_8ios_baseES4_
	.weak_def_can_be_hidden	__ZNSt3__116__pad_and_outputIcNS_11char_traitsIcEEEENS_19ostreambuf_iteratorIT_T0_EES6_PKS4_S8_S8_RNS_8ios_baseES4_
	.p2align	4, 0x90
__ZNSt3__116__pad_and_outputIcNS_11char_traitsIcEEEENS_19ostreambuf_iteratorIT_T0_EES6_PKS4_S8_S8_RNS_8ios_baseES4_: ## @_ZNSt3__116__pad_and_outputIcNS_11char_traitsIcEEEENS_19ostreambuf_iteratorIT_T0_EES6_PKS4_S8_S8_RNS_8ios_baseES4_
Lfunc_begin1:
	.cfi_startproc
	.cfi_personality 155, ___gxx_personality_v0
	.cfi_lsda 16, Lexception1
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	pushq	%r15
	pushq	%r14
	pushq	%r13
	pushq	%r12
	pushq	%rbx
	subq	$56, %rsp
	.cfi_offset %rbx, -56
	.cfi_offset %r12, -48
	.cfi_offset %r13, -40
	.cfi_offset %r14, -32
	.cfi_offset %r15, -24
	testq	%rdi, %rdi
	je	LBB2_19
## %bb.1:
	movq	%r8, %r12
	movq	%rcx, %r15
	movq	%rdi, %r13
	movl	%r9d, -68(%rbp)         ## 4-byte Spill
	movq	%rcx, %rax
	subq	%rsi, %rax
	movq	24(%r8), %rcx
	xorl	%r14d, %r14d
	subq	%rax, %rcx
	cmovgq	%rcx, %r14
	movq	%rdx, -88(%rbp)         ## 8-byte Spill
	movq	%rdx, %rbx
	subq	%rsi, %rbx
	testq	%rbx, %rbx
	jle	LBB2_3
## %bb.2:
	movq	(%r13), %rax
	movq	%r13, %rdi
	movq	%rbx, %rdx
	callq	*96(%rax)
	cmpq	%rbx, %rax
	jne	LBB2_19
LBB2_3:
	testq	%r14, %r14
	jle	LBB2_15
## %bb.4:
	movq	%r12, -80(%rbp)         ## 8-byte Spill
	xorps	%xmm0, %xmm0
	movaps	%xmm0, -64(%rbp)
	movq	$0, -48(%rbp)
	cmpq	$23, %r14
	jae	LBB2_8
## %bb.5:
	movl	%r14d, %eax
	addb	%r14b, %al
	movb	%al, -64(%rbp)
	leaq	-63(%rbp), %rbx
	movq	%rbx, %r12
	jmp	LBB2_9
LBB2_8:                                 ## %.thread.i.i.i
	leaq	16(%r14), %rbx
	andq	$-16, %rbx
	movq	%rbx, %rdi
	callq	__Znwm
	movq	%rax, %r12
	movq	%rax, -48(%rbp)
	orq	$1, %rbx
	movq	%rbx, -64(%rbp)
	movq	%r14, -56(%rbp)
	leaq	-63(%rbp), %rbx
LBB2_9:                                 ## %_ZNSt3__112basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEC1Emc.exit
	movzbl	-68(%rbp), %esi         ## 1-byte Folded Reload
	movq	%r12, %rdi
	movq	%r14, %rdx
	callq	_memset
	movb	$0, (%r12,%r14)
	testb	$1, -64(%rbp)
	je	LBB2_11
## %bb.10:                              ## %_ZNSt3__112basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEC1Emc.exit
	movq	-48(%rbp), %rbx
LBB2_11:                                ## %_ZNSt3__112basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEEC1Emc.exit
	movq	(%r13), %rax
Ltmp31:
	movq	%r13, %rdi
	movq	%rbx, %rsi
	movq	%r14, %rdx
	callq	*96(%rax)
Ltmp32:
	movq	-80(%rbp), %r12         ## 8-byte Reload
## %bb.12:                              ## %_ZNSt3__115basic_streambufIcNS_11char_traitsIcEEE5sputnEPKcl.exit
	movq	%rax, %rbx
	testb	$1, -64(%rbp)
	je	LBB2_14
## %bb.13:
	movq	-48(%rbp), %rdi
	callq	__ZdlPv
LBB2_14:                                ## %_ZNSt3__112basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEED1Ev.exit50
	cmpq	%r14, %rbx
	jne	LBB2_19
LBB2_15:
	movq	-88(%rbp), %rsi         ## 8-byte Reload
	subq	%rsi, %r15
	testq	%r15, %r15
	jle	LBB2_17
## %bb.16:
	movq	(%r13), %rax
	movq	%r13, %rdi
	movq	%r15, %rdx
	callq	*96(%rax)
	cmpq	%r15, %rax
	jne	LBB2_19
LBB2_17:
	movq	$0, 24(%r12)
	jmp	LBB2_20
LBB2_19:
	xorl	%r13d, %r13d
LBB2_20:
	movq	%r13, %rax
	addq	$56, %rsp
	popq	%rbx
	popq	%r12
	popq	%r13
	popq	%r14
	popq	%r15
	popq	%rbp
	retq
LBB2_21:
Ltmp33:
	movq	%rax, %rbx
	testb	$1, -64(%rbp)
	je	LBB2_23
## %bb.22:
	movq	-48(%rbp), %rdi
	callq	__ZdlPv
LBB2_23:                                ## %_ZNSt3__112basic_stringIcNS_11char_traitsIcEENS_9allocatorIcEEED1Ev.exit
	movq	%rbx, %rdi
	callq	__Unwind_Resume
	ud2
Lfunc_end1:
	.cfi_endproc
	.section	__TEXT,__gcc_except_tab
	.p2align	2
GCC_except_table2:
Lexception1:
	.byte	255                     ## @LPStart Encoding = omit
	.byte	255                     ## @TType Encoding = omit
	.byte	1                       ## Call site Encoding = uleb128
	.uleb128 Lcst_end1-Lcst_begin1
Lcst_begin1:
	.uleb128 Lfunc_begin1-Lfunc_begin1 ## >> Call Site 1 <<
	.uleb128 Ltmp31-Lfunc_begin1    ##   Call between Lfunc_begin1 and Ltmp31
	.byte	0                       ##     has no landing pad
	.byte	0                       ##   On action: cleanup
	.uleb128 Ltmp31-Lfunc_begin1    ## >> Call Site 2 <<
	.uleb128 Ltmp32-Ltmp31          ##   Call between Ltmp31 and Ltmp32
	.uleb128 Ltmp33-Lfunc_begin1    ##     jumps to Ltmp33
	.byte	0                       ##   On action: cleanup
	.uleb128 Ltmp32-Lfunc_begin1    ## >> Call Site 3 <<
	.uleb128 Lfunc_end1-Ltmp32      ##   Call between Ltmp32 and Lfunc_end1
	.byte	0                       ##     has no landing pad
	.byte	0                       ##   On action: cleanup
Lcst_end1:
	.p2align	2
                                        ## -- End function
	.section	__TEXT,__text,regular,pure_instructions
	.private_extern	___clang_call_terminate ## -- Begin function __clang_call_terminate
	.globl	___clang_call_terminate
	.weak_def_can_be_hidden	___clang_call_terminate
	.p2align	4, 0x90
___clang_call_terminate:                ## @__clang_call_terminate
## %bb.0:
	pushq	%rax
	callq	___cxa_begin_catch
	callq	__ZSt9terminatev
                                        ## -- End function
	.section	__TEXT,__cstring,cstring_literals
L_.str:                                 ## @.str
	.asciz	"something"


.subsections_via_symbols

	.section	__TEXT,__text,regular,pure_instructions
	.build_version macos, 10, 14	sdk_version 10, 14
	.globl	__Z9somethingv          ## -- Begin function _Z9somethingv
	.p2align	4, 0x90
__Z9somethingv:                         ## @_Z9somethingv
	.cfi_startproc
## %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	movl	$1, %eax
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function
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
	subq	$32, %rsp
	movl	$0, -4(%rbp)
	leaq	-20(%rbp), %rax
	movq	%rax, -16(%rbp)
	movl	$10, -20(%rbp)
	movl	$20, -24(%rbp)
	movq	-16(%rbp), %rax
	cmpl	$10, (%rax)
	jne	LBB1_2
## %bb.1:
	jmp	LBB1_2
LBB1_2:
	callq	__Z9somethingv
	addq	$32, %rsp
	popq	%rbp
	retq
	.cfi_endproc
                                        ## -- End function

.subsections_via_symbols

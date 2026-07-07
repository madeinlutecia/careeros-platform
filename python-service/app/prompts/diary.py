DIARY_TRANSFORM_PROMPT = """
Transforme o registro diário de trabalho em material profissional.

Registro:
- O que fiz: {what_i_did}
- Problemas resolvidos: {problems}
- Tecnologias: {technologies}
- Lições aprendidas: {lessons}

Retorne JSON com:
- resume_bullets: lista de bullets para currículo (formato STAR, com métricas quando possível)
- linkedin_update: texto sugerido para atualização do LinkedIn
- interview_points: pontos para usar em entrevistas
- achievements: conquistas para o banco de conquistas
"""
